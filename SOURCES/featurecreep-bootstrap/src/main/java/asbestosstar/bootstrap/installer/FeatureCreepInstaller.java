package asbestosstar.bootstrap.installer;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/** Basic GUI installer. The application selector is intentionally extensible; Minecraft is implemented first. */
public final class FeatureCreepInstaller {
    private static final String DEFAULT_MINECRAFT_VERSION = "26.4";
    private static final String INSTALL_SERVER_FLAG = "--install-server";

    private FeatureCreepInstaller() {}

    public static void launch(String[] args) {
        Path serverTarget = serverInstallTarget(args);
        if (serverTarget != null) {
            System.exit(installServerFromCommandLine(serverTarget));
        }
        SwingUtilities.invokeLater(() -> {
            installMotifIfAvailable();
            createFrame().setVisible(true);
        });
    }

    /**
     * Returns the directory requested by {@code --install-server}, the current directory when the
     * flag has no value, or null when the flag is absent.
     */
    static Path serverInstallTarget(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.equals(INSTALL_SERVER_FLAG)) {
                boolean hasValue = i + 1 < args.length && !args[i + 1].startsWith("--");
                return hasValue ? Path.of(args[i + 1]) : Path.of("").toAbsolutePath();
            }
            if (arg.startsWith(INSTALL_SERVER_FLAG + "=")) {
                String value = arg.substring(INSTALL_SERVER_FLAG.length() + 1);
                return value.isBlank() ? Path.of("").toAbsolutePath() : Path.of(value);
            }
        }
        return null;
    }

    static int installServerFromCommandLine(Path target) {
        String version = InstallerBundleMetadata.fixedMinecraftVersion();
        if (version == null) version = DEFAULT_MINECRAFT_VERSION;
        try {
            MinecraftInstallSupport.InstallResult result =
                    MinecraftInstallSupport.installServer(target, version, new ArtifactResolver());
            System.out.println("Installed " + result.name() + " into " + target.toAbsolutePath().normalize());
            System.out.println("Metadata: " + result.metadata());
            System.out.println("Review eula.txt, then start with run-featurecreep-server.sh or run-featurecreep-server.bat.");
            return 0;
        } catch (Throwable t) {
            System.err.println("Server installation failed: " + t);
            t.printStackTrace(System.err);
            return 1;
        }
    }

    static void installMotifIfAvailable() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (info.getName().toLowerCase().contains("motif") || info.getClassName().toLowerCase().contains("motif")) {
                    UIManager.setLookAndFeel(info.getClassName());
                    return;
                }
            }
        } catch (Exception e) {
            System.err.println("[FeatureCreep Installer] Could not select Motif LookAndFeel: " + e);
        }
    }

    private static JFrame createFrame() {
        String fixedMinecraftVersion = InstallerBundleMetadata.fixedMinecraftVersion();
        boolean fixedBundle = fixedMinecraftVersion != null;

        JFrame frame = new JFrame(fixedBundle
                ? "FeatureCreep " + MinecraftInstallSupport.FC_VERSION + " Installer - Minecraft " + fixedMinecraftVersion
                : "FeatureCreep " + MinecraftInstallSupport.FC_VERSION + " Installer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 520);
        frame.setLocationRelativeTo(null);

        InstallerConsole console = new InstallerConsole();
        console.attach();
        console.log("FeatureCreep installer ready. Pick a location and press Install; full output appears here and in featurecreep-install.log inside the target directory.");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);
        g.anchor = GridBagConstraints.WEST;
        g.fill = GridBagConstraints.HORIZONTAL;

        JComboBox<String> application = new JComboBox<>(new String[] { "Minecraft" });
        JComboBox<String> mode = new JComboBox<>(new String[] { "Vanilla Launcher / TLauncher", "Minecraft Server" });
        JComboBox<String> version = fixedBundle ? null
                : new JComboBox<>(MinecraftInstallSupport.SUPPORTED_VERSIONS.toArray(String[]::new));
        if (version != null) version.setSelectedItem(DEFAULT_MINECRAFT_VERSION);
        JTextField location = new JTextField(MinecraftInstallSupport.defaultMinecraftDirectory().toString(), 38);
        JButton browse = new JButton("Browse…");
        JButton install = new JButton("Install");

        int row = 0;
        add(panel, g, row++, "Application", application);
        add(panel, g, row++, "Install type", mode);
        if (!fixedBundle) {
            add(panel, g, row++, "Minecraft version", version);
        }

        g.gridx = 0; g.gridy = row; g.weightx = 0; panel.add(new JLabel("Location"), g);
        g.gridx = 1; g.weightx = 1; panel.add(location, g);
        g.gridx = 2; g.weightx = 0; panel.add(browse, g);
        g.gridx = 1; g.gridy = row + 1; panel.add(install, g);

        mode.addActionListener(e -> {
            if (mode.getSelectedIndex() == 0) {
                location.setText(MinecraftInstallSupport.defaultMinecraftDirectory().toString());
            } else {
                location.setText(Path.of(System.getProperty("user.home"), "featurecreep-minecraft-server").toString());
            }
        });

        browse.addActionListener(e -> {
            JFileChooser fc = new JFileChooser(location.getText());
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) location.setText(fc.getSelectedFile().getAbsolutePath());
        });

        install.addActionListener(e -> {
            install.setEnabled(false);
            console.clear();
            Path target = Path.of(location.getText());
            console.setLogFile(target.resolve("featurecreep-install.log"));
            console.log("Installing FeatureCreep " + (mode.getSelectedIndex() == 0 ? "client" : "server")
                    + " into " + target + " ...");
            try {
                ArtifactResolver resolver = new ArtifactResolver();
                String v = fixedBundle ? fixedMinecraftVersion : String.valueOf(version.getSelectedItem());
                MinecraftInstallSupport.InstallResult result = mode.getSelectedIndex() == 0
                        ? MinecraftInstallSupport.installClient(Path.of(location.getText()), v, resolver)
                        : MinecraftInstallSupport.installServer(Path.of(location.getText()), v, resolver);
                console.log("Installation finished: " + result.name() + " (metadata: " + result.metadata() + ")");
                JOptionPane.showMessageDialog(frame,
                        "Installed " + result.name() + "\nMetadata: " + result.metadata() +
                        (mode.getSelectedIndex() == 1 ? "\nThe official vanilla server JAR was resolved automatically. Review eula.txt before starting." : ""),
                        "FeatureCreep", JOptionPane.INFORMATION_MESSAGE);
            } catch (Throwable t) {
                console.log("Installation failed: " + t);
                JOptionPane.showMessageDialog(frame, t.toString(), "Installation failed", JOptionPane.ERROR_MESSAGE);
                t.printStackTrace(System.err);
            } finally {
                install.setEnabled(true);
            }
        });

        frame.add(panel, BorderLayout.NORTH);
        frame.add(console.component(), BorderLayout.CENTER);
        return frame;
    }

    private static void add(JPanel panel, GridBagConstraints g, int row, String label, java.awt.Component component) {
        g.gridx = 0; g.gridy = row; g.weightx = 0; panel.add(new JLabel(label), g);
        g.gridx = 1; g.weightx = 1; panel.add(component, g);
    }
}

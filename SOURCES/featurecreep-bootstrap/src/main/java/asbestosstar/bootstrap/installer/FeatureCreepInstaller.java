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
    private FeatureCreepInstaller() {}

    public static void launch(String[] args) {
        SwingUtilities.invokeLater(() -> {
            installMotifIfAvailable();
            createFrame().setVisible(true);
        });
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
                ? "FeatureCreep 12 Installer - Minecraft " + fixedMinecraftVersion
                : "FeatureCreep 12 Installer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, fixedBundle ? 255 : 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);
        g.anchor = GridBagConstraints.WEST;
        g.fill = GridBagConstraints.HORIZONTAL;

        JComboBox<String> application = new JComboBox<>(new String[] { "Minecraft" });
        JComboBox<String> mode = new JComboBox<>(new String[] { "Vanilla Launcher / TLauncher", "Minecraft Server" });
        JComboBox<String> version = fixedBundle ? null
                : new JComboBox<>(MinecraftInstallSupport.SUPPORTED_VERSIONS.toArray(String[]::new));
        if (version != null) version.setSelectedItem("26.4");
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
            try {
                ArtifactResolver resolver = new ArtifactResolver();
                String v = fixedBundle ? fixedMinecraftVersion : String.valueOf(version.getSelectedItem());
                MinecraftInstallSupport.InstallResult result = mode.getSelectedIndex() == 0
                        ? MinecraftInstallSupport.installClient(Path.of(location.getText()), v, resolver)
                        : MinecraftInstallSupport.installServer(Path.of(location.getText()), v, resolver);
                JOptionPane.showMessageDialog(frame,
                        "Installed " + result.name() + "\nMetadata: " + result.metadata() +
                        (mode.getSelectedIndex() == 1 ? "\nThe official vanilla server JAR was resolved automatically. Review eula.txt before starting." : ""),
                        "FeatureCreep", JOptionPane.INFORMATION_MESSAGE);
            } catch (Throwable t) {
                JOptionPane.showMessageDialog(frame, t.toString(), "Installation failed", JOptionPane.ERROR_MESSAGE);
                t.printStackTrace(System.err);
            } finally {
                install.setEnabled(true);
            }
        });

        frame.add(panel, BorderLayout.CENTER);
        return frame;
    }

    private static void add(JPanel panel, GridBagConstraints g, int row, String label, java.awt.Component component) {
        g.gridx = 0; g.gridy = row; g.weightx = 0; panel.add(new JLabel(label), g);
        g.gridx = 1; g.weightx = 1; panel.add(component, g);
    }
}

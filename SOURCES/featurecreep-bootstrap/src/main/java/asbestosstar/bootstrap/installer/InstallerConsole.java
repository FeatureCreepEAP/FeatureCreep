package asbestosstar.bootstrap.installer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 * In-GUI console for the installer. Tee-wires System.out and System.err so every
 * [FeatureCreep] message printed during installation is visible in the window,
 * and optionally mirrors the same lines to a log file inside the target
 * directory.
 */
final class InstallerConsole {
    private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final JTextArea area = new JTextArea(8, 60);
    private final JScrollPane scroll;
    private Path logFile;

    InstallerConsole() {
        area.setEditable(false);
        area.setFont(new java.awt.Font(java.awt.Font.MONOSPACED, java.awt.Font.PLAIN, 12));
        scroll = new JScrollPane(area,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    java.awt.Component component() {
        return scroll;
    }

    /** Logs one line to the console (and to the log file when one is set). */
    void log(String message) {
        append("[" + LocalTime.now().format(TIME) + "] " + message + "\n");
    }

    /** Starts capturing System.out/System.err into the console (and the real console). */
    void attach() {
        PrintStream out = new PrintStream(new Tee(System.out), true, StandardCharsets.UTF_8);
        PrintStream err = new PrintStream(new Tee(System.err), true, StandardCharsets.UTF_8);
        System.setOut(out);
        System.setErr(err);
    }

    void setLogFile(Path logFile) {
        this.logFile = logFile;
    }

    void clear() {
        SwingUtilities.invokeLater(() -> area.setText(""));
    }

    private void append(String line) {
        SwingUtilities.invokeLater(() -> {
            area.append(line);
            area.setCaretPosition(area.getDocument().getLength());
        });
        Path target = logFile;
        if (target != null) {
            try {
                Files.createDirectories(target.toAbsolutePath().getParent());
                Files.writeString(target, line, StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                logFile = null;
                appendDirect("[" + LocalTime.now().format(TIME) + "] [FeatureCreep Installer] Logging to file disabled: " + e + "\n");
            }
        }
    }

    private void appendDirect(String line) {
        SwingUtilities.invokeLater(() -> {
            area.append(line);
            area.setCaretPosition(area.getDocument().getLength());
        });
    }

    /** Writes each completed line to the console with a timestamp. */
    private final class Tee extends OutputStream {
        private final PrintStream original;
        private final StringBuilder pending = new StringBuilder();

        Tee(PrintStream original) {
            this.original = original;
        }

        @Override
        public void write(int b) {
            original.write(b);
            synchronized (pending) {
                if (b == '\n') {
                    flushLine();
                } else {
                    pending.append((char) b);
                }
            }
        }

        @Override
        public void write(byte[] b, int off, int len) {
            original.write(b, off, len);
            synchronized (pending) {
                for (int i = off; i < off + len; i++) {
                    if (b[i] == '\n') {
                        flushLine();
                    } else {
                        pending.append((char) b[i]);
                    }
                }
            }
        }

        private void flushLine() {
            String line = pending.toString();
            pending.setLength(0);
            if (!line.isEmpty()) append("[" + LocalTime.now().format(TIME) + "] " + line + "\n");
        }
    }
}

package utils;

import com.jcraft.jsch.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

public class HandleDataBase {

    public static void main(String[] args){
        String jumpHost = "10.118.70.126";
        String jumpUsername = "nwadmin";
        String jumpPassword = "@rakuten";

        String targetHost = "aws-devops-socket";
        String targetPassword = "bbWXeGxs";


        try {
            JSch jsch = new JSch();

            // Connect to the jump server
            Session jumpSession = jsch.getSession(jumpUsername, jumpHost, 22);
            jumpSession.setPassword(jumpPassword);
            jumpSession.setConfig("StrictHostKeyChecking", "no");
            jumpSession.setTimeout(60000);
            jumpSession.connect();
            System.out.println("Connected to the jump server");

            // Set up port forwarding from the jump server to the target server
            int forwardedPort = jumpSession.setPortForwardingL(0, targetHost, 22);
            System.out.println("Port forwarding set up on port: " + forwardedPort);

            // Connect to the target server through the forwarded port
            Session targetSession = jsch.getSession("", "localhost", forwardedPort);
            targetSession.setPassword(targetPassword);
            targetSession.setConfig("StrictHostKeyChecking", "no");
            targetSession.setTimeout(60000);
            targetSession.connect();
            System.out.println("Connected to the target server");

            executeCommandsUsingShell(targetSession);
            // Disconnect from target server
            targetSession.disconnect();
            System.out.println("Disconnected from target server");

            // Disconnect from jump server
            jumpSession.disconnect();
            System.out.println("Disconnected from jump server");

        } catch (JSchException e) {
            System.err.println("JSchException occurred: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static void sendCommand(OutputStream out, String command) throws Exception {
        out.write((command + "\n").getBytes());
        out.flush();
    }

    private static void executeCommandsUsingShell(Session session) throws Exception {
        ChannelShell channelShell = (ChannelShell) session.openChannel("shell");

        // Get input and output streams
        InputStream in = channelShell.getInputStream();
        OutputStream out = channelShell.getOutputStream();
        PrintStream shellStream = new PrintStream(out, true);

        // Start the shell channel
        channelShell.connect();

        // Commands to execute
        String[] commands = {
                "use pushdb_01;\n",
               "select * from push_device_info A order by A.last_login_dt desc limit 1;\n"
        };

        // Buffered reader for reading command outputs
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        // Send commands
        for (String command : commands) {
            System.out.println("Sending command: " + command.trim());
            shellStream.println(command);
            shellStream.flush();

            // Wait for some output before sending the next command
            Thread.sleep(1000); // Adjust the sleep time as needed
        }

        // Read command output
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // Disconnect channel
        channelShell.disconnect();
    }
}


package utils;

import com.jcraft.jsch.*;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OMSExecution {

    public void executeOMS(String stockCode, String currentPrice, String quantity, String operationType, String date) {
        String jumpHost = "10.118.70.126";
        String jumpUsername = "webap";
        String jumpPassword = "@rakuten";

        String targetHost = "10.21.41.69";
        String targetUsername = "m.test";
        String targetPassword = "!m.test";
        String passwordToEnter = "oms-den"; // Password to send when prompted

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
            Session targetSession = jsch.getSession(targetUsername, "localhost", forwardedPort);
            targetSession.setPassword(targetPassword);
            targetSession.setConfig("StrictHostKeyChecking", "no");
            targetSession.setTimeout(60000);
            targetSession.connect();
            System.out.println("Connected to the target server");

            // Execute commands using shell channel
            executeCommandsUsingShell(targetSession, passwordToEnter, stockCode, currentPrice, quantity, operationType, date);

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

    private static void executeCommandsUsingShell(Session session, String passwordToSend, String account, String currentPrice, String quantity, String operationType, String date) throws Exception {
        ChannelShell channelShell = (ChannelShell) session.openChannel("shell");
        String date1 = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println("The sysdate is:" + date1);

        // Get input and output streams
        InputStream in = channelShell.getInputStream();
        OutputStream out = channelShell.getOutputStream();
        PrintStream shellStream = new PrintStream(out, true);

        // Start the shell channel
        channelShell.connect();

        // Determine commands based on the operation type
        String[] commands;
        if ("buy".equalsIgnoreCase(operationType)) {
            commands = new String[]{
                    "cd /mnt/4TB/00-mw-testdriver/01-common\n",
                    String.format("sh searchAndExecuteOMSDen-EXA11_X7.sh 1 %s\n", account),
                    passwordToSend + "\n",
                    String.format("sh searchAndExecuteOMSDen-EXA11_X7.sh 4 %s\n", account),
                    passwordToSend + "\n",
                    String.format("sh searchAndExecuteOMSDen-EXA11_X7.sh 6 %s %s %s\n", account, currentPrice, quantity),
                    passwordToSend + "\n"
            };
        } else if ("sell".equalsIgnoreCase(operationType)) {
            commands = new String[]{
                    "cd /mnt/4TB/00-mw-testdriver/01-common\n",
                    String.format("sh searchAndExecuteOMSDen-EXA11_X7.sh 1 %s\n", account),
                    passwordToSend + "\n",
                    String.format("sh searchAndExecuteOMSDen-EXA11_X7.sh 4 %s\n", account),
                    passwordToSend + "\n",
                    String.format("sh searchAndExecuteOMSDen-EXA11_X7.sh 5 %s %s %s\n", account, currentPrice, quantity),
                    passwordToSend + "\n"
            };
        } else if ("us buy".equalsIgnoreCase(operationType)) {
            commands = new String[]{
                    "cd /mnt/4TB/00-mw-testdriver/01-common\n",
                    String.format("sh searchAndExecuteOMSDen-us.sh 1 %s\n", account),
                    passwordToSend + "\n",
                    String.format("sh searchAndExecuteOMSDen-us.sh 3 %s %s %s %s\n", account, currentPrice, quantity, date1 + "000000"),
                    passwordToSend + "\n"
            };
        } else if ("us sell".equalsIgnoreCase(operationType)) {
            commands = new String[]{
                    "cd /mnt/4TB/00-mw-testdriver/01-common\n",
                    String.format("sh searchAndExecuteOMSDen-us.sh 1 %s\n", account),
                    passwordToSend + "\n",
                    String.format("sh searchAndExecuteOMSDen-us.sh 3 %s %s %s %s\n", account, currentPrice, quantity, date1 + "000000"),
                    passwordToSend + "\n"
            };
        } else {
            throw new IllegalArgumentException("Invalid operation type: " + operationType);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        for (String command : commands) {
            System.out.println("Sending command: " + command);
            shellStream.println(command);
            shellStream.flush();
            Thread.sleep(1000);
        }

        channelShell.disconnect();
    }

    @Test
    public void testExecuteOMS() {
        String stockCode = "12345";
        String currentPrice = "100.50";
        String quantity = "10";
        String operationType = "us buy";
        String date = "20241211";

        executeOMS(stockCode, currentPrice, quantity, operationType, date);
    }
}

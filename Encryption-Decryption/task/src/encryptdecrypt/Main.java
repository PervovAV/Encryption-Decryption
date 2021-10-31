package encryptdecrypt;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        EncryptorDecryptor encryptorDecryptor = new EncryptorDecryptor();
        HashMap<String, String> arguments = new HashMap<>();

        for (int i = 0; i < args.length; i += 2) {
            arguments.put(args[i], args[i + 1]);
        }

        String mode = arguments.getOrDefault("-mode", "enc");
        int key = Integer.valueOf(arguments.getOrDefault("-key", "0"));
        String data = arguments.getOrDefault("-data", "");
        String algorithm = arguments.getOrDefault("-alg", "shift");
        String filePathToWrite = arguments.getOrDefault("-out", "");
        String filePathToRead = arguments.getOrDefault("-in", "");

        encryptorDecryptor.startCommandLineArgs(mode, key, data, algorithm, filePathToWrite, filePathToRead);
    }
}

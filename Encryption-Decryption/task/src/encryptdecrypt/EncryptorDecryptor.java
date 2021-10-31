package encryptdecrypt;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class EncryptorDecryptor {

    public void startCommandLineArgs(String mode, int key, String data, String algorithm,
                                     String filePathToWrite, String filePathToRead) {
        String curData = "";
        if (!"".equals(data) && !"".equals(filePathToRead)) {
            curData = data;
        } else if (!"".equals(data)) {
            curData = data;
        } else if (!"".equals(filePathToRead)) {
            try {
                curData = readDataFromFile(filePathToRead);
            } catch (FileNotFoundException e) {
                System.out.println("Error");
                return;
            }
        }

        switch (mode) {
            case "enc" :
                curData = encrypt(curData, key, algorithm);
                break;
            case "dec" :
                curData = decrypt(curData, key, algorithm);
                break;
        }

        printData(curData, filePathToWrite);
    }

    private String encrypt(String str, int key, String algorithm) {
        StringBuilder sb = new StringBuilder();
        if ("shift".equals(algorithm)) {

            for (int i = 0; i < str.length(); i++) {
                char curCh = str.charAt(i);
                if (Character.isLetter(curCh)) {
                    if (Character.isLowerCase(curCh)) {
                        if (curCh + key > 'z') {
                            curCh = (char)('a' + curCh + key - 'z' - 1);
                            sb.append(curCh);
                        } else {
                            sb.append((char)(curCh + key));
                        }
                    } else {
                        if (curCh + key > 'Z') {
                            curCh = (char)('A' + curCh + key - 'Z' - 1);
                            sb.append(curCh);
                        } else {
                            sb.append((char)(curCh + key));
                        }
                    }
                } else {
                    sb.append(curCh);
                }
            }

        } else {
            for (int i = 0; i < str.length(); i++) {
                char curCh = str.charAt(i);
                sb.append((char)(curCh + key));
            }
        }

        return sb.toString();
    }

    private String decrypt(String str, int key, String algorithm) {
        StringBuilder sb = new StringBuilder();
        if ("shift".equals(algorithm)) {

            for (int i = 0; i < str.length(); i++) {
                char curCh = str.charAt(i);
                if (Character.isLetter(curCh)) {
                    if (Character.isLowerCase(curCh)) {
                        if (curCh - key < 'a') {
                            curCh = (char)('z' + curCh - key - 'a' + 1);
                            sb.append(curCh);
                        } else {
                            sb.append((char)(curCh - key));
                        }
                    } else {
                        if (curCh - key < 'A') {
                            curCh = (char)('Z' + curCh - key - 'A' + 1);
                            sb.append(curCh);
                        } else {
                            sb.append((char)(curCh - key));
                        }
                    }
                } else {
                    sb.append(curCh);
                }
            }

        } else {
            for (int i = 0; i < str.length(); i++) {
                char curCh = str.charAt(i);
                sb.append((char)(curCh - key));
            }
        }

        return sb.toString();
    }

    private void printData(String data, String path) {
        if ("".equals(path)) {
            System.out.println(data);
        } else {
            try (PrintWriter printWriter = new PrintWriter(path)) {
                printWriter.println(data);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private String readDataFromFile(String filePathToWrite) throws FileNotFoundException {
        String data = "";

        try (Scanner scanner = new Scanner(new File(filePathToWrite))) {
            data = scanner.nextLine();
        }

        return data;
    }
}

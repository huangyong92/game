package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class GameRunnerTest {

    @Test
    public void testGame() {
        try {
            savePrint("currentOut.txt");
            String rightOut = getStrFromFile("rightOut.txt");
            String currentOut = getStrFromFile("currentOut.txt");
            Assert.assertEquals(rightOut, currentOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void savePrint(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        PrintStream fileStream = new PrintStream(file);
        System.setOut(fileStream);
        GameRunner.main(null);

        if (fileStream != null) {
            fileStream.close();
        }

        PrintStream sysout = System.out;
        System.setOut(sysout);
    }

    public String getStrFromFile(String filePath) throws IOException {
        if (filePath == null) {
            return "";
        }

        File file = new File(filePath);
        Long fileLength = file.length();
        byte[] fileCotent = new byte[fileLength.intValue()];

        FileInputStream inputStream = new FileInputStream(file);
        inputStream.read(fileCotent);
        if (inputStream != null) {
            inputStream.close();
        }

        return new String(fileCotent, "utf-8");
    }

}

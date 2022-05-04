//Anthony Franklin afranklin18@cnm.edu
//FranklinP8
//04/07/2022
//HighlanderEnigma.java

package com.cis2235.franklin.franklinp7;

import java.util.Random;

public class HighlanderEnigma extends Enigma {
    private Random random;
    private String message, codedMessage;
    private int key;
    private int[] macLeod = new int[]{37, 19, 86};

    public HighlanderEnigma()
    {}

    private void encode()
    {
        StringBuilder s = new StringBuilder(super.getCodedMessage());
        char [] sArray = new char[message.length()];
        message.getChars(0, message.length(), sArray, 0);
        int temp = 0;
        int first;
        int second;
        for (int i = 0; i < s.length(); i++)
        {
            if (i < 3)
            {
                super.setMessage(String.valueOf(s.charAt(i)), macLeod[i]);
                s.replace(i, i + 1, super.getCodedMessage());
                continue;
            }
            first = sArray[temp];
            second = sArray[temp + 1];
            super.setMessage(String.valueOf(s.charAt(i)), (first - second));
            s.replace(i, i + 1, super.getCodedMessage());
            temp++;
        }
        codedMessage = String.valueOf(s);
    }

    private void decode()
    {
        StringBuilder s = new StringBuilder(codedMessage);
        char [] sArray = new char[codedMessage.length()];
        codedMessage.getChars(0, codedMessage.length(), sArray, 0);
        int temp = 0;
        int first;
        int second;
        for (int i = 0; i < s.length(); i++)
        {
            if (i < 3)
            {
                super.setCodedMessage(String.valueOf(s.charAt(i)), macLeod[i]);
                s.replace(i, i + 1, super.getMessage());
                continue;
            }
            first = s.charAt(temp);
            second = s.charAt(temp + 1);
            super.setCodedMessage(String.valueOf(s.charAt(i)), (first - second));
            s.replace(i, i + 1, super.getMessage());
            temp++;
        }
        super.setCodedMessage(String.valueOf(s), key);
        message = super.getMessage();
    }
    @Override
    public void setMessage(String mess, int k)
    {
        super.setMessage(mess, k);
        message = mess;
        key = k;
        encode();
    }

    @Override
    public void setMessage(String mess)
    {
        random = new Random();
        int k = random.nextInt(1,51);
        this.setMessage(mess, k);
    }


    @Override
    public void setCodedMessage(String code, int k)
    {
        key = k;
        codedMessage = code;
        decode();
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    @Override
    public String getCodedMessage()
    {
        return codedMessage;
    }
    @Override
    public int getKey() {
        return key;
    }
}
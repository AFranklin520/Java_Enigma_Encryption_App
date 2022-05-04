//Anthony Franklin afranklin18@cnm.edu
//FranklinP7
//03/22/2022
//Enigma.java

package com.cis2235.franklin.franklinp7;

import java.util.Random;

public class Enigma implements EnigmaInterface{
    private String message, codedMessage;
    private int key;
    private Random rand;

    //Constructor
    public Enigma()
    {
        message = "";
        codedMessage = "";
        key = 1;

        rand = new Random();
    }

    private void encode()
    {
        int minVal = 32;
        int maxVal = 126;
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < message.length(); i++)
        {
            int temp = message.charAt(i) + key;
            if( temp < minVal)
            {
                temp = maxVal - (minVal - temp) +1;
                str.append((char)temp);
            }
            else if(temp > maxVal)
            {
                temp = (temp - (maxVal + 1)) + minVal;
                str.append((char)temp);
            }
            else str.append((char)temp);
        }
        codedMessage = str.toString();
    }

    private void decode()
    {
        int minVal = 32;
        int maxVal = 126;
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < codedMessage.length(); i++)
        {
            int temp = codedMessage.charAt(i) - key;
            if( temp < minVal)
            {
                temp = maxVal - (minVal - temp) +1;
                str.append((char)temp);
            }
            else if(temp > maxVal)
            {
                temp = (temp - (maxVal + 1)) + minVal;
                str.append((char)temp);
            }
            else str.append((char)temp);
        }
        message = str.toString();
    }
    @Override
    public void setMessage(String mess, int k)
    {
        message = mess;
        //if (k > 50 || k < 1) key = 1;
        key = k;
        encode();
    }

    @Override
    public void setMessage(String mess)
    {
        message = mess;
        key = rand.nextInt(1,51);
        encode();
    }

    @Override
    public void setCodedMessage(String code, int key)
    {
        codedMessage = code;
        this.key = key;
        decode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getCodedMessage() {
        return codedMessage;
    }

    @Override
    public int getKey() {
        return key;
    }
}

//Anthony Franklin afranklin18@cnm.edu
//FranklinP8
//04/07/2022
//GEnigma.java

package com.cis2235.franklin.franklinp7;

import java.util.Random;

public class GEnigma extends Enigma {
    private Random random;
    private String message, codedMessage;
    private int key;

    public GEnigma()
    {}

    private void encode()
    {
        StringBuilder s = new StringBuilder(super.getCodedMessage());
        random = new Random(key);
        for(int i = 0; i < s.length(); i++)
        {
            Enigma e = new Enigma();
            int temp = random.nextInt(1, 51);
            if (i % 2 == 0) {
                e.setMessage(String.valueOf(s.charAt(i)), temp);
                s.replace(i,i+1,e.getCodedMessage());
            } else {
                e.setCodedMessage(String.valueOf(s.charAt(i)), temp);
                s.replace(i,i+1,e.getMessage());
            }
        }
        codedMessage = s.reverse().toString();
    }

    private void decode()
    {
        StringBuilder temp = new StringBuilder(codedMessage);
        temp.reverse();
        codedMessage = String.valueOf(temp);
        super.setCodedMessage(codedMessage, key);
        StringBuilder s = new StringBuilder(super.getMessage());
        random = new Random(key);
        for(int i = 0; i < s.length(); i++)
        {
            if (!(i % 2 == 0)) {
                super.setMessage(String.valueOf(s.charAt(i)), random.nextInt(1, 51));
                s.replace(i,i+1,super.getCodedMessage());
            } else {
                super.setCodedMessage(String.valueOf(s.charAt(i)), random.nextInt(1, 51));
                s.replace(i,i+1,super.getMessage());
            }
        }
        message = s.toString();
    }
    @Override
    public void setMessage(String mess, int k)
    {
        super.setMessage(mess, k);
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
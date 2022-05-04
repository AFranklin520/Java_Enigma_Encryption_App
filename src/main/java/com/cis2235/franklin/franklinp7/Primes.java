//Anthony Franklin afranklin18@cnm.edu
//FranklinP8
//04/07/2022
//Primes.java

package com.cis2235.franklin.franklinp7;

import java.util.Random;

public class Primes extends Enigma {

    private int[] primes = new int[] {11, 13, 17, 19, 23};
    private String message, codedMessage;
    private int key;

    public Primes()
    {}
    private void encode()
    {
        String str = super.getCodedMessage();
        StringBuilder s = new StringBuilder();
        int count = 0;
        for(char c:str.toCharArray())
        {
            count = count == 5? 0 : count;
            super.setMessage(String.valueOf(c), primes[count]);
            s.append(super.getCodedMessage());
            codedMessage = String.valueOf(s);
            count++;
        }
        super.setMessage(codedMessage, key);
        codedMessage = super.getCodedMessage();


        int temp = key % 4 + 2;
        s = new StringBuilder(codedMessage);
        String tempS = "";
        while(!(s.isEmpty()) && !(s.length() < temp))
        {
            StringBuilder tempSB = new StringBuilder(s.substring(0, temp));
            tempSB.reverse();
            tempS += tempSB;
            s.delete(0, temp);
        }
        s.reverse();
        tempS += s;
        codedMessage = tempS;
    }

    private void decode()
    {
        int count = 0;
        int temp = key % 4 + 2;
        StringBuilder s = new StringBuilder(codedMessage);
        String tempS = "";
        while(!(s.isEmpty()) && !(s.length() < temp))
        {
            StringBuilder tempSB = new StringBuilder(s.substring(0, temp));
            tempSB.reverse();
            tempS += tempSB;
            s.delete(0, temp);
        }
        s.reverse();
        tempS += s;
        s.delete(0,1);
        message = tempS;
        super.setCodedMessage(message, key);
        message = super.getMessage();

        for(char c:message.toCharArray())
        {
            count = count == 5? 0 : count;
            super.setCodedMessage(String.valueOf(c), primes[count]);
            s.append(super.getMessage());
            count++;
        }
        super.setCodedMessage(String.valueOf(s), key);
        message = super.getMessage();

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
        Random random = new Random();
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
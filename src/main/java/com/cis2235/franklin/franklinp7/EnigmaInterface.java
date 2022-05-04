//Anthony Franklin afranklin18@cnm.edu
//FranklinP7
//03/22/2022
//EnigmaInterface.java

package com.cis2235.franklin.franklinp7;

public interface EnigmaInterface {
    void setMessage(String mess, int key);
    void setMessage(String mess);
    void setCodedMessage(String mess, int key);
    String getMessage();
    String getCodedMessage();
    int getKey();
}

package com.younessharaki.apricot.Model;

/**
 * Created by Youness Haraki on 25.04.2018.
 */

public class Type {
    private  String Name ;
    private  String Image ;

    public Type(){

    }

    public Type(String name , String image )
    {
        Name=name;
        Image=image;
    }
    public String getName()
    {
        return Name;
    }
    public void setName(String name)
    {
        Name = name;
    }
    public String getImage()
    {
        return Image;
    }

}

/*===============================================================================
Copyright (c) 2016 PTC Inc. All Rights Reserved.

Copyright (c) 2012-2014 Qualcomm Connected Experiences, Inc. All Rights Reserved.

Vuforia is a trademark of PTC Inc., registered in the United States and other 
countries.
===============================================================================*/

package com.vuforia.samples.SampleApplication.utils;
import android.content.res.AssetManager;
import java.nio.Buffer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Joyoung extends MeshObject
{

    private Buffer mVertBuff;
    private Buffer mTexCoordBuff;
    private Buffer mNormBuff;
    private Buffer mIndBuff;
    private AssetManager assetManager;

    private int indicesNumber = 0;
    private int verticesNumber = 0;


    public Joyoung(AssetManager inputassetManager)
    {
        this.assetManager = inputassetManager;
        setVerts();
        setTexCoords();
        setNorms();
    }

    private double[] loadDataFromTxt(String fileName) throws IOException{
        InputStream inputFile = null;
        inputFile = assetManager.open(fileName);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputFile));

        String line = reader.readLine();
        int floatsToRead = Integer.parseInt(line);
        double [] data = new double[3*floatsToRead];
        for (int i = 0; i < floatsToRead; i++)
            {

                String curline = reader.readLine();
                data[i] = Float.parseFloat(curline);
            }
        return data;

     }


    private void setVerts()
    {
        double[] TEAPOT_VERTS ={0};
        try{
            TEAPOT_VERTS = loadDataFromTxt("ImageTargets/verts.txt");
        } catch(IOException e){
            e.printStackTrace();
        }
        mVertBuff = fillBuffer(TEAPOT_VERTS);
        verticesNumber = TEAPOT_VERTS.length / 3;
    }
    
    
    private void setTexCoords()
    {
        double[] TEAPOT_TEX_COORDS = {0};
        try{
            TEAPOT_TEX_COORDS = loadDataFromTxt("ImageTargets/texcoods.txt");
        } catch(IOException e){
            e.printStackTrace();
        }
        mTexCoordBuff = fillBuffer(TEAPOT_TEX_COORDS);
        
    }
    
    
    private void setNorms()
    {
        double[] TEAPOT_NORMS = {0};
        try{
            TEAPOT_NORMS = loadDataFromTxt("ImageTargets/norms.txt");
        } catch(IOException e){
            e.printStackTrace();
        }
        mNormBuff = fillBuffer(TEAPOT_NORMS);
    }
    
    public int getNumObjectIndex()
    {
        return indicesNumber;
    }
    
    
    @Override
    public int getNumObjectVertex()
    {
        return verticesNumber;
    }
    
    
    @Override
    public Buffer getBuffer(BUFFER_TYPE bufferType)
    {
        Buffer result = null;
        switch (bufferType)
        {
            case BUFFER_TYPE_VERTEX:
                result = mVertBuff;
                break;
            case BUFFER_TYPE_TEXTURE_COORD:
                result = mTexCoordBuff;
                break;
            case BUFFER_TYPE_NORMALS:
                result = mNormBuff;
                break;
            case BUFFER_TYPE_INDICES:
                result = mIndBuff;
            default:
                break;
        
        }
        
        return result;
    }
    
}

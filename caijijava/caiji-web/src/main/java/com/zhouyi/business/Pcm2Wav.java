package com.zhouyi.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class Pcm2Wav {

	
	
	public static void main(String[] args) throws Exception {
		
		File dir = new File("D:\\voicedictation\\检察院单机版\\pcm\\录音文件");
        File[] files = dir.listFiles();
        File latestFile = files[0];
        for(File file : files)
        {
            if(file.lastModified() > latestFile.lastModified())
            {
                latestFile = file;
            }
        }

        System.out.println("latest file is " + latestFile);
        System.out.println( latestFile.getAbsolutePath());
        parse(latestFile.getAbsolutePath(), "d:/qqqqqq.wav");
    }

    public static void parse(String source, String target) throws Exception {
        float sampleRate = 16000;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = false;
        AudioFormat af = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
        File sourceFile = new File(source);
        FileOutputStream out = new FileOutputStream(new File(target));
        AudioInputStream audioInputStream = new AudioInputStream(new FileInputStream(sourceFile), af, sourceFile.length());
        AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, out);
        audioInputStream.close();
        out.flush();
        out.close();
    }
    
}

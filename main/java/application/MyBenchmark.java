/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

//import application.MainController;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;



@Measurement(iterations=5,time=5)
@Warmup(iterations=3,time=5)
@Fork(value=1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class MyBenchmark {
//int x=0;
Image testImage;

@Setup(Level.Invocation)
public void setup() {
	File f;	
	try {
	
		f=new File("C:\\Users\\IoT97\\Desktop\\BSc IoT (Level 8 Hounours)\\Semester 4 (IoT Year 2)\\Data Structures & Algorithms II\\JMHBenchmarkPerformanceTesting\\src\\main\\java\\application\\vformation.jpg");
		if(f!=null)
			{
			testImage=new Image(new FileInputStream(f));
			//Image x=convertToGrayscale(testImage);
			}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

@Benchmark
public void testGrayscaleConversion() {

	
			Image x=convertToGrayscale(testImage);

}



public  Image convertToGrayscale(Image image) {
	PixelReader pixelReader = image.getPixelReader(); //imageView.getImage().getPixelReader();
	
	int width = (int) image.getWidth();
	int height = (int) image.getHeight();
	
	WritableImage grayImage = new WritableImage(width, height);
	PixelWriter pixelWriter = grayImage.getPixelWriter();
	for(int i = 0; i <width; i++) {
		for(int j = 0; j <height; j++){
			
			int color = pixelReader.getArgb(i, j);
			
			int r = (color >> 16 ) & 0xff;
			int g = (color >> 8) & 0xff;
			int b = color & 0xff;
			int gray = (int) (0.299 * r + 0.587 * g + 0.114 * b);
			
			int newPixel = argbToPixel(255, gray, gray, gray);
			
			pixelWriter.setArgb(i, j, newPixel);
		}
	}	
	return grayImage;
}


@Benchmark
public void testBandWConversion() {
	
	Image y=convertToBandWscale(testImage);
}



public static Image convertToBandWscale(Image image) {
	PixelReader pixelReader = image.getPixelReader(); //imageView.getImage().getPixelReader();

	int width = (int) image.getWidth();
	int height = (int) image.getHeight();

	WritableImage bAndWImage = new WritableImage(width, height);
	PixelWriter pixelWriter = bAndWImage.getPixelWriter();
	
	for(int i = 0; i <width; i++) {
		for(int j = 0; j <height; j++){

			int color = pixelReader.getArgb(i, j);

			int alpha = ((color >> 24 ) & 0xff);
			int r = ((color >> 16 ) & 0xff);
			int g = ((color >> 8) & 0xff);
			int b = (color & 0xff);
			
			int rgb = (r + g + b) / 3;		
			
			if(rgb > 127) {
				color = (alpha<<24)|(255<<16)|(255<<8)|255;
			}
			
			else if (rgb <=127) {
				color = (alpha<<24)|(0<<16)|(0<<8)|0;
			}
			
			pixelWriter.setArgb(i, j, color);
			
		}
		
		}
	return bAndWImage;
}



private static int argbToPixel(int alpha, int red, int green, int blue) {
	
	int newPixel = 0;
	
	newPixel += alpha;
			newPixel = newPixel << 8;
	newPixel += red;
			newPixel = newPixel << 8;
	newPixel += green;
			newPixel = newPixel << 8;
	newPixel += blue;

	return newPixel;
}

}
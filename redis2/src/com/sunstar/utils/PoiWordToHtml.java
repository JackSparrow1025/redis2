package com.sunstar.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.junit.Test;
import org.w3c.dom.Document;


//word.doc转html，前提要不加密
public class PoiWordToHtml {


@Test	 
public static void wordToHtml(String outPath) throws Throwable {

	final String path = "D:\\";
	final String file = "test.doc";
	 // String outPath="E:\\myeclipse2015about\\apache-tomcat-7.0.81\\webapps\\Redis2";
	  InputStream input = new FileInputStream(path + file);
	  HWPFDocument wordDocument = new HWPFDocument(input);
	  WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
	    DocumentBuilderFactory.newInstance().newDocumentBuilder()
	      .newDocument());
	  wordToHtmlConverter.setPicturesManager(new PicturesManager() {
	  
	   public String savePicture(byte[] content, PictureType pictureType,
	     String suggestedName, float widthInches, float heightInches) {		   
	    return suggestedName;
	   }
	  });
	  wordToHtmlConverter.processDocument(wordDocument);
	  List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();
	  if (pics != null) {
	   for (int i = 0; i < pics.size(); i++) {
	    Picture pic = (Picture) pics.get(i);
	    try {
	     pic.writeImageContent(new FileOutputStream(outPath
	       +"\\"+ pic.suggestFullFileName()));
	    } catch (FileNotFoundException e) {
	     e.printStackTrace();
	    }
	   }
	  }
	  Document htmlDocument = wordToHtmlConverter.getDocument();
	  ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	  DOMSource domSource = new DOMSource(htmlDocument);
	  StreamResult streamResult = new StreamResult(outStream);
	  TransformerFactory tf = TransformerFactory.newInstance();
	  Transformer serializer = tf.newTransformer();
	  serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
	  serializer.setOutputProperty(OutputKeys.INDENT, "yes");
	  serializer.setOutputProperty(OutputKeys.METHOD, "html");
	  serializer.transform(domSource, streamResult);
	  outStream.close();
	  String content = new String(outStream.toByteArray());

	  FileUtils.writeStringToFile(new File(outPath, "MyHtml.html"), content, "utf-8");
	  
	
}	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}

package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import play.libs.XML;

public class XmlHandler {

	public static Document getXml(String dir) {
		Document doc = null;
		try {
			File fXmlFile = new File(dir);
			InputStream is = new FileInputStream(fXmlFile);
			doc = XML.fromInputStream(is, "UTF-8");
			//DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			//DocumentBuilder dBuilder;
			//dBuilder = dbFactory.newDocumentBuilder();
			//doc = dBuilder.parse(fXmlFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
}

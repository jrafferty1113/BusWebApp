package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.w3c.dom.Document;

import play.libs.XML;

public class XmlHandler {

	public static Document getXml(String dir) {
		Document doc = null;
		try {
			File fXmlFile = new File(dir);
			InputStream is = new FileInputStream(fXmlFile);
			doc = XML.fromInputStream(is, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
}

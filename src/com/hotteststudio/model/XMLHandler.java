package com.hotteststudio.model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;
import com.thoughtworks.xstream.mapper.MapperWrapper;

public class XMLHandler {
	public XStream xstream;
	
	public XMLHandler() {
		xstream = new XStream(new DomDriver()) {
            protected MapperWrapper wrapMapper(MapperWrapper next) {
                return new MapperWrapper(next) {
                    public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                        try {
                            return definedIn != Object.class || realClass(fieldName) != null;
                        } catch(CannotResolveClassException cnrce) {
                            return false;
                        }
                    }
                };
            }
        };
        
        //configSchema();
	}
	
	public void configSchema() {
		//xstream.alias("ArrayOfBookMetadata", ArrayOfBookMetadata.class);
		//xstream.addImplicitCollection(ArrayOfBookMetadata.class, "bookMetadata");
		//xstream.aliasField("Highlights", BookMetadata.class, "hlList");
	}
	
	/*
	public void deserialize(String xml) {
		try {
			UserSettings settings = (UserSettings)xstream.fromXML(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}

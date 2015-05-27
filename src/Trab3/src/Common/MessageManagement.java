/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.netbeans.xml.schema.updateschema.ObjectFactory;
import org.netbeans.xml.schema.updateschema.TMyPlace;

/**
 *
 * @author
 * André
 */
public class MessageManagement {

    public static String createPlaceStateContent(TMyPlace myPlace) throws JAXBException {
        //Aula 2
        StringWriter ret = new StringWriter();
        ObjectFactory factory = new ObjectFactory();
        
        JAXBContext jaxbContext = JAXBContext.newInstance(TMyPlace.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        JAXBElement<TMyPlace> element = factory.createMyPlace(myPlace);
        jaxbMarshaller.marshal(element, ret);
        return ret.toString();

    }

    public static TMyPlace retrievePlaceStateObject(String content) throws JAXBException {
        //Aula 2
        
        JAXBContext jaxbContext = JAXBContext.newInstance(TMyPlace.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(content);
        
        JAXBElement<TMyPlace> element = (JAXBElement) jaxbUnmarshaller.unmarshal(new StreamSource(reader),TMyPlace.class);
        TMyPlace myPlace = element.getValue();      
    
        return myPlace;
    }
}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.04 at 11:16:23 AM BST 
//


package org.netbeans.xml.schema.updateschema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tPlace complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tPlace">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Gress" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Wolf" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Cow" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Obstacle" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Entity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Position" type="{http://xml.netbeans.org/schema/updateSchema}tPosition"/>
 *         &lt;element name="Ttl" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tPlace", propOrder = {
    "gress",
    "wolf",
    "cow",
    "obstacle",
    "entity",
    "position",
    "ttl"
})
public class TPlace {

    @XmlElement(name = "Gress")
    protected int gress;
    @XmlElement(name = "Wolf")
    protected boolean wolf;
    @XmlElement(name = "Cow")
    protected boolean cow;
    @XmlElement(name = "Obstacle")
    protected boolean obstacle;
    @XmlElement(name = "Entity", required = true)
    protected String entity;
    @XmlElement(name = "Position", required = true)
    protected TPosition position;
    @XmlElement(name = "Ttl")
    protected int ttl;

    /**
     * Gets the value of the gress property.
     * 
     */
    public int getGress() {
        return gress;
    }

    /**
     * Sets the value of the gress property.
     * 
     */
    public void setGress(int value) {
        this.gress = value;
    }

    /**
     * Gets the value of the wolf property.
     * 
     */
    public boolean isWolf() {
        return wolf;
    }

    /**
     * Sets the value of the wolf property.
     * 
     */
    public void setWolf(boolean value) {
        this.wolf = value;
    }

    /**
     * Gets the value of the cow property.
     * 
     */
    public boolean isCow() {
        return cow;
    }

    /**
     * Sets the value of the cow property.
     * 
     */
    public void setCow(boolean value) {
        this.cow = value;
    }

    /**
     * Gets the value of the obstacle property.
     * 
     */
    public boolean isObstacle() {
        return obstacle;
    }

    /**
     * Sets the value of the obstacle property.
     * 
     */
    public void setObstacle(boolean value) {
        this.obstacle = value;
    }

    /**
     * Gets the value of the entity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntity() {
        return entity;
    }

    /**
     * Sets the value of the entity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntity(String value) {
        this.entity = value;
    }

    /**
     * Gets the value of the position property.
     * 
     * @return
     *     possible object is
     *     {@link TPosition }
     *     
     */
    public TPosition getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPosition }
     *     
     */
    public void setPosition(TPosition value) {
        this.position = value;
    }

    /**
     * Gets the value of the ttl property.
     * 
     */
    public int getTtl() {
        return ttl;
    }

    /**
     * Sets the value of the ttl property.
     * 
     */
    public void setTtl(int value) {
        this.ttl = value;
    }

}

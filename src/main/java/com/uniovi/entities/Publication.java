package com.uniovi.entities;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Publication {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "photo", nullable = true)
    private byte[] photo;

    public Publication() {
    }

    public Publication(String title, String text, User user) {
	super();
	this.title = title;
	this.text = text;
	this.user = user;
	this.fechaCreacion = new Date();
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public User getUser() {
	return user;
    }

    protected void _setUser(User user) {
	this.user = user;
    }

    public Date getFechaCreacion() {
	return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
	this.fechaCreacion = fechaCreacion;
    }

    public Long getId() {
	return id;
    }

    public byte[] getPhoto() {
	return photo;
    }

    public void setPhoto(byte[] photo) {
	this.photo = photo;
    }

    @Override
    public String toString() {
	return "Publication [id=" + id + ", title=" + title + ", texto=" + text + ", user=" + user + ", fechaCreacion="
		+ fechaCreacion + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Publication other = (Publication) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }
}

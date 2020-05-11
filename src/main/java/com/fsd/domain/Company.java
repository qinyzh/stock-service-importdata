package com.fsd.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "company_code")
    private String companycode;

    @Column(name = "company_name")
    private String companyname;

    @Column(name = "turnover")
    private BigDecimal turnover;

    @Column(name = "ceo")
    private String ceo;

    @Column(name = "board_of_director")
    private String boardofdirector;

    @Column(name = "listed_in_se")
    private String listedinse;

    @Column(name = "sector_name")
    private String sectorname;

    @Column(name = "brief_write_up")
    private String writeup;

    @Column(name = "stock_code")
    private String stockcode;

    @Column(name = "company_status")
    private String companystatus;

    public Company() {
    }

    public Company(String companyname) {
        setCompanyname(companyname);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public BigDecimal getTurnover() {
        return turnover;
    }

    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getBoardofdirector() {
        return boardofdirector;
    }

    public void setBoardofdirector(String boardofdirector) {
        this.boardofdirector = boardofdirector;
    }

    public String getListedinse() {
        return listedinse;
    }

    public void setListedinse(String listedinse) {
        this.listedinse = listedinse;
    }

    public String getSectorname() {
        return sectorname;
    }

    public void setSectorname(String sectorname) {
        this.sectorname = sectorname;
    }

    public String getWriteup() {
        return writeup;
    }

    public void setWriteup(String writeup) {
        this.writeup = writeup;
    }

    public String getStockcode() {
        return stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode;
    }

    public String getCompanystatus() {
        return companystatus;
    }

    public void setCompanystatus(String companystatus) {
        this.companystatus = companystatus;
    }

}
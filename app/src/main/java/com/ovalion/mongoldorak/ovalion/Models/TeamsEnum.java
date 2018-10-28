package com.ovalion.mongoldorak.ovalion.Models;

import static android.content.Context.MODE_PRIVATE;

public enum TeamsEnum {
    AGEN("Agen","Sporting union Agen lot et garonne","sr:competitor:5747","19 Rue Pierre de Coubertin","age"),
    BORDEAUX_BEGLES("Bordeaux bègles","Union Bordeaux Bègles","sr:competitor:52813","25 Rue Delphin Loche","uni"),
    CASTRES("Castres","Castres olympique","sr:competitor:5752","Rue de Bisseous","cas"),
    CLERMONT("Clermont","ASM Clermont auvergne","sr:competitor:5753","35 Rue du Clos Four","asm"),
    GRENOBLE("Grenoble","FC Grenoble rugby","sr:competitor:74521","1 Avenue de Valmy","fcg"),
    LA_ROCHELLE("La Rochelle","Stade Rochelais","sr:competitor:43847","Avenue du Maréchal Juin","lar"),
    LYON("Lyon","Lyon olympique universitaire rugby","sr:competitor:52812","353 Avenue Jean Jaurès","lou"),
    MONTPELLIER("Montpellier","Montpellier hérault rugby","sr:competitor:5754","500 Avenue de Vanières","mon"),
    PARIS("Paris","Stade français Paris","sr:competitor:5758","1 Allée Charles Brennus","fpa"),
    PAU("Pau","Section paloise béarn pyrénées","sr:competitor:78973","Boulevard de l'aviation","sec"),
    PERPIGNAN("Perpignan","Union sportive arlequins perpignan","sr:competitor:4220","11 Allée Aimé Giral","usa"),
    RACING_92("Racing 92","Racing 92","sr:competitor:36538","99 Jardins de l'Arche","rac"),
    TOULON("Toulon","Rugby club toulonnais","sr:competitor:5760","Quai Joseph Lafontan","rct"),
    TOULOUSE("Toulouse","Stade toulousain rugby","sr:competitor:4217","114 Rue des Troènes","tou");

    private String name = "";
    private String full_name = "";
    private String id;
    private String location = "";
    private String abrev = "";

    //Constructeur
    TeamsEnum(String name, String full_name, String id,String location,String abrev){
        this.name = name;
        this.full_name = full_name;
        this.id = id;
        this.location = location;
        this.abrev = abrev;
    }

    @Override
    public String toString(){
        return name;
    }

    public String getFull_name(){
        return full_name;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public static TeamsEnum fromId(String id) {
        for (TeamsEnum t : TeamsEnum.values()) {
            if (t.getId().contains(id)) {
                return t;
            }
        }
        throw new IllegalArgumentException("No constant with text " + id + " found");
    }

    public static String getLocationbyId(String id){
        for (TeamsEnum t : TeamsEnum.values()) {
            if (t.getId().contains(id)) {
                return t.getLocation();
            }
        }
        return "11 Allée Aimé Giral";
    }

    public String getAbrev(){
        return abrev;
    }

    public static String getAbrevById(String id){
        for (TeamsEnum t : TeamsEnum.values()) {
            if (t.getId().contains(id)) {
                return t.getAbrev();
            }
        }
        return "fcg";
    }
}

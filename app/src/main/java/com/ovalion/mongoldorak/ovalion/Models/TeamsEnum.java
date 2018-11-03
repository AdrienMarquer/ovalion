package com.ovalion.mongoldorak.ovalion.Models;

import static android.content.Context.MODE_PRIVATE;

public enum TeamsEnum {
    AGEN("Agen","Sporting union Agen lot et garonne","sr:competitor:5747","19 Rue Pierre de Coubertin","age","44.200|0.63"),
    BORDEAUX_BEGLES("Bordeaux bègles","Union Bordeaux Bègles","sr:competitor:52813","25 Rue Delphin Loche","uni","44.837|-0.57"),
    CASTRES("Castres","Castres olympique","sr:competitor:5752","Rue de Bisseous","cas","43.360|2.15"),
    CLERMONT("Clermont","ASM Clermont auvergne","sr:competitor:5753","35 Rue du Clos Four","asm","45.777|3.08"),
    GRENOBLE("Grenoble","FC Grenoble rugby","sr:competitor:74521","1 Avenue de Valmy","fcg","45.188|5.72"),
    LA_ROCHELLE("La Rochelle","Stade Rochelais","sr:competitor:43847","Avenue du Maréchal Juin","lar","46.160|-1.15"),
    LYON("Lyon","Lyon olympique universitaire rugby","sr:competitor:52812","353 Avenue Jean Jaurès","lou","45.750|4.85"),
    MONTPELLIER("Montpellier","Montpellier hérault rugby","sr:competitor:5754","500 Avenue de Vanières","mon","43.610|3.87"),
    PARIS("Paris","Stade français Paris","sr:competitor:5758","1 Allée Charles Brennus","fpa","48.856|2.35"),
    PAU("Pau","Section paloise béarn pyrénées","sr:competitor:78973","Boulevard de l'aviation","sec","43.295|-0.37"),
    PERPIGNAN("Perpignan","Union sportive arlequins perpignan","sr:competitor:4220","11 Allée Aimé Giral","usa","42.688|2.89"),
    RACING_92("Racing 92","Racing 92","sr:competitor:36538","99 Jardins de l'Arche","rac","48.895 |2.22"),
    TOULON("Toulon","Rugby club toulonnais","sr:competitor:5760","Quai Joseph Lafontan","rct","43.124|5.92"),
    TOULOUSE("Toulouse","Stade toulousain rugby","sr:competitor:4217","114 Rue des Troènes","tou","43.604|1.44");

    private String name = "";
    private String full_name = "";
    private String id;
    private String location = "";
    private String abrev = "";
    private String pos = "";

    //Constructeur
    TeamsEnum(String name, String full_name, String id,String location,String abrev,String pos){
        this.name = name;
        this.full_name = full_name;
        this.id = id;
        this.location = location;
        this.abrev = abrev;
        this.pos = pos;
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
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
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

    public static String getCityById(String id){
        for (TeamsEnum t : TeamsEnum.values()) {
            if (t.getId().contains(id)) {
                return t.getName();
            }
        }
        return "fcg";
    }

    public static String getPosById(String id){
        for (TeamsEnum t : TeamsEnum.values()) {
            if (t.getId().contains(id)) {
                return t.getPos();
            }
        }
        return "43.604|1.44";
    }
}

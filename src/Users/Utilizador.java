package Users;

import Geral.GPS;
import Interfaces.Login;
import Stock.Encomenda;
import Stock.EncomendaRealizadaUtilizador;
import Stock.EncomendaRealizadaVoluntario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Utilizador extends User implements Login {

    GPS gps; // Geral.GPS guardar par de coordenadas latitude,longitude;
    private String nome;
    private List<Encomenda> on_hold;
    private List<EncomendaRealizadaUtilizador> encomendas_realizadas;


    /**
     * Construtor padrão.
     */
    public Utilizador() {
        super();
        this.nome = "";
        this.gps = new GPS();
        this.on_hold = new ArrayList<>();
        this.encomendas_realizadas = new ArrayList<>();
    }

    /**
     * Construtor por cópia.
     *
     * @param u Users.Utilizador.
     */
    public Utilizador(Utilizador u) {
        super(u);
        this.nome = u.getNome();
        this.gps = u.getGps();
        this.on_hold = u.getOnHold();
        this.encomendas_realizadas = u.getEncomendasFeitas();
    }

    /**
     * Construtor por parâmetros.
     *
     * @param codigo   Código de Users.Utilizador.
     * @param nome     Nome do Users.Utilizador.
     * @param password Password do Users.Utilizador.
     * @param gps      Localização do Users.Utilizador.
     */
    public Utilizador(String codigo, String nome, String password, GPS gps) {
        super(codigo,password);
        this.nome = nome;
        this.gps = gps;
        this.encomendas_realizadas = new ArrayList<>();
        this.on_hold = new ArrayList<>();
    }

    /**
     * Obtém a password do Users.Utilizador
     *
     * @return String
     */
    public String getPassword() {
        return super.getPassword();
    }

    /**
     * Definir a password do Users.Utilizador
     *
     * @param password Password do Users.Utilizador
     */
    public void setPassword(String password) {
        super.setPassword(password);
    }

    /**
     * Obtém o código do Users.Utilizador
     *
     * @return String
     */
    public String getCodigo() {
        return super.getCode();
    }

    /**
     * Definir o Código do Users.Utilizador
     *
     * @param codigo Código do Users.Utilizador
     */
    public void setCodigo(String codigo) {
        super.setCode(codigo);
    }

    /**
     * Obtém o nome do Users.Utilizador
     *
     * @return String
     */
    public String getNome() {
        return nome;
    }

    /**
     * Definir o Nome do Users.Utilizador
     *
     * @param nome Nome do Users.Utilizador
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o Geral.GPS do Users.Utilizador
     *
     * @return Geral.GPS
     */
    public GPS getGps() {
        return this.gps;
    }

    /**
     * Definir o Geral.GPS do Users.Utilizador
     *
     * @param g Geral.GPS do Users.Utilizador
     */
    public void setGps(GPS g) {
        this.gps = g;
    }

    /**
     * Método que verifica se o login de um Users.Utilizador é correto
     * @param code que é o código inserido pelo utilizador.
     * @param pass que é a password inserida pelo utilizador.
     * @return Validação dos dados inseridos.
     */
    @Override
    public boolean checkLogin(String code, String pass) {
        return (super.getCode().compareTo(code) == 0 && super.getPassword().compareTo(pass) == 0);
    }

    /**
     * Método que devolve as encomendas por entregar de um utilizador.
     * @return Lista com as encomendas.
     */
    public List<Encomenda> getOnHold(){
        return this.on_hold.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    /**
     * Método que define a lista de encomendas por entregar de um utilizador.
     * @param a Lista com as encomendas.
     */
    public void setOnHold(List<Encomenda> a){
        a.stream().map(Encomenda::clone).forEach(v -> this.on_hold.add(v));
    }

    /**
     * Método que adiciona uma encomenda à lista de espera do utilizador.
     * @param e Encomenda feita.
     */
    public void addEncomendaOnHold(Encomenda e){
        this.on_hold.add(e);
    }

    /**
     * Método que devolve as encomendas feitas por um utilizador.
     * @return List com as encomendas feitas.
     */
    public List<EncomendaRealizadaUtilizador> getEncomendasFeitas(){
        return this.encomendas_realizadas.stream().map(EncomendaRealizadaUtilizador::clone).collect(Collectors.toList());
    }

    /**
     * Método que define as encomendas feitas por um utilizador.
     * @param a List com as encomendas feitas.
     */
    public void setEncomendasFeitas(List<EncomendaRealizadaUtilizador> a){
        a.stream().map(EncomendaRealizadaUtilizador::clone).forEach(v -> this.encomendas_realizadas.add(v));
    }

    /**
     * Converte um Utilizador numa String
     *
     * @return String
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Utilizador:").append(super.getCode()).append(",").
                append(this.nome).append(",").append(this.gps.getLatitude()).append(",").append(this.gps.getLongitude());
        return sb.toString();
    }

    public String toStringShow(){
        return "Utilizador:"+
                "\n{ Código: "+this.getCodigo()+
                "\n  Nome: "+this.getNome()+
                "\n  Localização: "+this.getGps().toString()+
                "\n}";
    }
    /**
     * Cria uma cópia do Users.Utilizador
     *
     * @return Users.Utilizador
     */
    public Utilizador clone() {
        return new Utilizador(this);
    }

    /**
     * Verificar se um dado Objeto é igual a este Utilizaodr
     *
     * @param o Objeto
     * @return boolean
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilizador that = (Utilizador) o;
        return Objects.equals(getCodigo(), that.getCodigo()) &&
                Objects.equals(getNome(), that.getNome()) &&
                Objects.equals(getPassword(), that.getPassword()) &&
                Objects.equals(getGps(), that.getGps());
    }

}
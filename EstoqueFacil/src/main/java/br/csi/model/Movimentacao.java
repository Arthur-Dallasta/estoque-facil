package br.csi.model;

import java.sql.Timestamp;

public class Movimentacao {
    private int id;
    private String tipo; // ENTRADA ou SAIDA
    private int produtoId;
    private int usuarioId;
    private int quantidade;
    private Timestamp dataMovimentacao;
    private String observacao;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getProdutoId() { return produtoId; }
    public void setProdutoId(int produtoId) { this.produtoId = produtoId; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public Timestamp getDataMovimentacao() { return dataMovimentacao; }
    public void setDataMovimentacao(Timestamp dataMovimentacao) { this.dataMovimentacao = dataMovimentacao; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
} 
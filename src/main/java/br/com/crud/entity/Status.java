package br.com.crud.entity;

public enum Status {
  IN_ANALYSIS("em análise"),
  ANALYSIS_FINISHED("análise realizada"),
  ANALYSIS_APPROVED("análise aprovada"),
  STARTED("iniciado"),
  PLANED("planejado"),
  IN_PROGRESS("em andamento"),
  DONE("encerrado"),
  CANCELED("cancelado");

  private final String value;

  Status(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}

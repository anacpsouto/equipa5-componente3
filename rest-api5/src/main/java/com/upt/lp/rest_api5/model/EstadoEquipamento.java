package com.upt.lp.rest_api5.model;

public enum EstadoEquipamento {
    PENDENTE,  // Equipamento ainda não avaliado pelo admin
    ACEITE,    // Equipamento aceito para doação
    RECUSADO,  // Equipamento recusado para doação
    DOADO;     // Equipamento já doado

    // Método opcional para exibir uma descrição mais amigável
    @Override
    public String toString() {
        switch (this) {
            case PENDENTE:
                return "Pendente";
            case ACEITE:
                return "Aceite";
            case RECUSADO:
                return "Recusado";
            case DOADO:
                return "Doado";
            default:
                return super.toString();
        }
    }
}

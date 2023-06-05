package com.goesbernardo.recebepagamentos.domain;

import lombok.Data;


public enum Status {

    ENVIADO, PROCESSANDO, AUTORIZADO, NEGADO, CANCELADO;
}

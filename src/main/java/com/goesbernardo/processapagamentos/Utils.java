package com.goesbernardo.processapagamentos;

import com.goesbernardo.processapagamentos.domain.TipoBandeira;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Utils {

    public boolean validarNumeroCartao(String numCartao) {
        Integer numString;
        int soma = 0;
        if (numCartao.length() <= 15) {
            for (int i = 0; i < numCartao.length(); i++) {
                numString = Integer.parseInt(numCartao.substring(i, i + 1));
                if (i % 2 == 0) {
                    soma += numString;
                } else {
                    if ((numString * 2) > 9) {
                        soma += ((numString * 2) - 9);
                    } else {
                        soma += (numString * 2);
                    }
                }
            }
        }
        if (numCartao.length() >= 16) {
            for (int i = 0; i < numCartao.length(); i++) {
                numString = Integer.parseInt(numCartao.substring(i, i + 1));
                if (i % 2 == 0) {
                    if (numString * 2 > 9) {
                        soma += (numString * 2 - 9);
                    } else {
                        soma += (numString * 2);
                    }
                } else {
                    soma += numString;
                }
            }
        }
        return soma % 10 == 0;
    }

    public boolean validarCvvCartao(String cvv) {
        if (Objects.isNull(cvv)) {
            return false;
        }

        try {
            cvv = cvv.trim();
            if (Integer.parseInt(cvv) < 1 || cvv.length() != 3) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public boolean validarDataValidade(LocalDate dataValidade) {
        if (Objects.isNull(dataValidade) || LocalDate.now().isAfter(dataValidade)) {
            return false;
        }
        return true;
    }

    public TipoBandeira identificarBandeira(String numCartao) {
        List<String> listaElo = Arrays.asList("636368", "438935", "504175", "451416", "509048", "509067", "509049",
                "509069", "509050", "509074", "509068", "509040", "509045", "509051", "509046", "509066", "509047",
                "509042", "509052", "509043", "509064", "509040", "36297", "5067", "4576", "4011");

        if ("38".equals(numCartao.substring(0, 2)) || "60".equals(numCartao.substring(0, 2))) {
            return TipoBandeira.HIPERCARD;
        }

        if ("4".equals(numCartao.substring(0, 1))) {
            return TipoBandeira.VISA;
        }

        if ("5".equals(numCartao.substring(0, 1))) {
            return TipoBandeira.MASTERCARD;
        }

        return TipoBandeira.DESCONHECIDO;
    }




}
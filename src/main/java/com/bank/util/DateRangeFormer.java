package com.bank.util;

import com.bank.model.Deposit;
import com.bank.model.Operation;
import com.bank.model.Transfer;
import com.bank.model.dto.DepositDTO;
import com.bank.model.dto.OperationDTO;
import com.bank.model.dto.TransferDTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateRangeFormer {

    public static List<TransferDTO> formTransfers(List<Transfer> transfers, String from, String to)
            throws ParseException {
        List<TransferDTO> dtos = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fromDateRequest = dateFormat.parse(from);
        Date toDateRequest = dateFormat.parse(to);
        for (Transfer transfer: transfers) {
            Date date = dateFormat.parse(transfer.getDate());
            if (date.getTime() >= fromDateRequest.getTime() &&
                    date.getTime() <= toDateRequest.getTime()) {
                dtos.add(new TransferDTO(transfer));
            }
        }
        return dtos;
    }

    public static List<DepositDTO> formDeposits(List<Deposit> deposits, String from, String to)
            throws ParseException {
        List<DepositDTO> dtos = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fromDateRequest = dateFormat.parse(from);
        Date toDateRequest = dateFormat.parse(to);
        for (Deposit deposit: deposits) {
            Date date = dateFormat.parse(deposit.getDate());
            if (date.getTime() >= fromDateRequest.getTime() &&
                    date.getTime() <= toDateRequest.getTime()) {
                dtos.add(new DepositDTO(deposit));
            }
        }
        return dtos;
    }

}

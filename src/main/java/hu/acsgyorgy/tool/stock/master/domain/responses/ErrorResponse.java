package hu.acsgyorgy.tool.stock.master.domain.responses;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorResponse {

    private boolean successful;
    private String message;
}

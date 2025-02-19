package io.github.alberes.guestdb.controller.exception;

import io.github.alberes.guestdb.controller.dto.FieldErroDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StardardError implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long timestamp;

    private Integer status;

    private String error;

    private String message;

    private String path;

    private List<FieldErroDto> fields;

}
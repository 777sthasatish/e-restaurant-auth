package com.cotiviti.erestaurantauth.controller.open;

import com.satish.fieldvalidator.domain.api.FieldServicePort;
import com.satish.fieldvalidator.webcore.dto.FieldDTO;
import com.satish.fieldvalidator.webcore.mapper.FieldMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import static com.cotiviti.erestaurantauth.utils.constants.APIConstants.*;
import static com.satish.fieldvalidator.restadapter.util.constants.APIPathConstant.*;

@RestController
@RequestMapping(BASE_API + PUBLIC + FIELD)
@RequiredArgsConstructor
public class PublicFieldController {
    private final FieldServicePort fieldServicePort;
    private final FieldMapper fieldMapper;

    @GetMapping
    public ResponseEntity<List<FieldDTO>> getActiveFields() {
        return ResponseEntity.ok(fieldMapper.toTargetList(fieldServicePort.getActiveFields()));
    }
}

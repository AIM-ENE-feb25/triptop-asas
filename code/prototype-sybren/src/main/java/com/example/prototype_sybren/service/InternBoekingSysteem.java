package com.example.prototype_sybren.service;

import com.example.prototype_sybren.domain.Overnachting;
import com.example.prototype_sybren.dto.OvernachtingResponse;
import io.micrometer.common.lang.Nullable;
import org.hibernate.sql.ast.tree.expression.Over;
import org.intellij.lang.annotations.Language;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class InternBoekingSysteem {

    private final JdbcTemplate database;

    public InternBoekingSysteem(JdbcTemplate jdbcTemplate) {
        this.database = jdbcTemplate;
    }

    private final RowMapper<OvernachtingResponse> overnachtingResponseRowMapper = (resultSet, unused) -> new OvernachtingResponse(
            resultSet.getInt("overnachting_id"),
            resultSet.getString("naam"),
            resultSet.getString("hotel_naam"),
            resultSet.getTimestamp("begin_datum"),
            resultSet.getTimestamp("eind_datum")
    );


    @Nullable
    public List<OvernachtingResponse> haalOvernachtingenOp(Overnachting overnachting) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDatum = overnachting.getBeginDatum();
        String beginDatumString = sdf.format(beginDatum);
        Date eindDatum = overnachting.getEindDatum();
        String eindDatumString = sdf.format(eindDatum);

        @Language("TSQL")
        final String sql = """
            SELECT * FROM Overnachting o
            LEFT JOIN Bestemming b 
            ON o.bestemming_id = b.bestemming_id
           WHERE b.naam LIKE ? AND o.begin_datum = ? AND o.eind_datum = ?
        """;
        String bestemming = overnachting.getBestemming();
        bestemming = '%' + bestemming + '%';

        return this.database.query(sql, this.overnachtingResponseRowMapper, bestemming, beginDatumString, eindDatumString);
    }
}

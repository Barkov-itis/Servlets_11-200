package itis.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import itis.models.FileInfo;

import javax.sql.DataSource;
import java.util.List;

public class FilesRepositoryJdbcImpl implements FilesRepository {

    private DataSource dataSource;

    //language=SQL
    private final static String SQL_INSERT = "insert into file(storage_file_name, original_file_name, type, size) " +
            "values (?, ?, ?, ?)";

    //language=SQL
    private final static String SQL_SELECT_BY_ID = "select * from file where id = ?";

    private final static String SQL_SELECT = "select * from file";

    private JdbcTemplate jdbcTemplate;

    public FilesRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<FileInfo> fileRowMapper = (row, rowNumber) ->
            FileInfo.builder()
                    .id(row.getLong("id"))
                    .originalFileName(row.getString("original_file_name"))
                    .storageFileName(row.getString("storage_file_name"))
                    .size(row.getLong("size"))
                    .type(row.getString("type"))
                    .build();


    @Override
    public void save(FileInfo entity) {
        // jdbc template реализовать через PrepareStatement
        jdbcTemplate.update(SQL_INSERT, entity.getStorageFileName(), entity.getOriginalFileName(),
                entity.getType(),
                entity.getSize());
    }

    @Override
    // реализовать через PreparedStatement
    public FileInfo findById(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, fileRowMapper, id);
    }

//    @Override
//    public List<FileInfo> findAll() {
////        return jdbcTemplate.queryForList(SQL_SELECT);
//        return null;
//    }
}

package est.day9AlbumDemo.dto.imports;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserImportDto {

    private Long id;
    private String name;
    private String email;
    private String phone;

    @JsonProperty("company")
    private Company company;

    @JsonProperty("address")
    private Address address;

    @Getter
    @NoArgsConstructor
    public static class Company {
        private String name;
    }

    @Getter
    @NoArgsConstructor
    public static class Address {
        private String city;
    }
}
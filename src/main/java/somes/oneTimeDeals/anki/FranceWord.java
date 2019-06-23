package somes.oneTimeDeals.anki;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FranceWord {
    private String france;
    private String pronunciation;
    private String description;
    private String english;
    private String examples;
}

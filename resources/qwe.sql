            select
              user_login as login,
              sum(total-1) as total,
              sum(signaturePresent-1) filter(where signaturePresent>1) as signaturePresent,
              sum(damaged-1) filter(where damaged>1) as damaged,
              sum(signatureAndDamagePresent-1) filter(where signatureAndDamagePresent>1) as signatureAndDamagePresent,
              sum(damageChargeCount-1) filter(where damageChargeCount>1)  as damageChargeCount,
              sum(majorDamage-1) filter(where majorDamage>1)  as majorDamage,
              sum(majorDamageAndSignature-1) filter(where majorDamageAndSignature>1)  as majorDamageAndSignature

            from (  select
                count(*) as total,
                count(*) filter(where is_signature_present = true and (has_denial_reason is null or has_denial_reason = false)) as signaturePresent,
                count(*) filter(where damage_count >0 ) as damaged,
                count(*) filter(where is_signature_present = true and (has_denial_reason is null or has_denial_reason = false) and damage_count >0)
                               as signatureAndDamagePresent,
                count(*) filter(where cost_charged > 0.005) as damageChargeCount,
              count(*) filter(where has_major_damage = true ) as majorDamage,
              count(*) filter(where has_major_damage = true and is_signature_present = true
                       and (has_denial_reason is null or has_denial_reason = false)) as majorDamageAndSignature,
                user_login,
                ra_number
                from movement
                   where organization = :organization
                       and timestamp >= :dateFrom
                       and timestamp < :dateTo
                       and (:station is null or station = :station)
              group by user_login, ra_number
              having count(*) > 1
              order by user_login) as qwe
            group by user_login
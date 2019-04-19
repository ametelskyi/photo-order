SELECT
  (sum(cost_charged) * 100) AS "total",
  substring(time, 0, 11)    AS "day",
  organization              AS "organization",
  station                   AS "station",
  process                   AS "process"
FROM movement
WHERE timestamp >= ? AND timestamp < ? AND process IN (?) AND state IN (?) AND (? IS NULL OR organization = ?) AND
      (? IS NULL OR station = ?) AND (? IS NULL OR user_login = ?) AND (? IS NULL OR ra_number = ?) AND
      (? IS NULL OR ra_supercover = ?) AND ra_number IS NOT NULL AND cost_charged IS NOT NULL
GROUP BY day, organization, station, process
ORDER BY day, organization, station




match (v:Vehicle{vin:'ZFA3120000JA84963'})-[rel:LAST_RA]->(:RA) delete rel;


match (lc:Lifecycle{uuid:'fdedf3a4-2065-4082-88dd-cb7ef8de393e'})-[:UNDER]->(ra:RA)
with lc, ra
         match (v:Vehicle{vin:'ZFA3120000JA84963'})
create (v)-[rel:LAST_RA]->(ra)

====


match (v:Vehicle{vin:'ZFA3120000JA84963'})-[rel:LAST_EQUIPMENT]->(:Equipments) delete rel;


match (lc:Lifecycle{uuid:'fdedf3a4-2065-4082-88dd-cb7ef8de393e'})-[:HAVING_EQUIPMENTS]->(el:Equipments)
with lc, el
         match (v:Vehicle{vin:'ZFA3120000JA84963'})
create (v)-[:HAVING_EQUIPMENTS]->(el)

====

match (v:Vehicle{vin:'ZFA3120000JA84963'})-[rel:LAST_INCIDENTS]->(:Incidents) delete rel;


match (lc:Lifecycle{uuid:'fdedf3a4-2065-4082-88dd-cb7ef8de393e'})-[:HAVING_INCIDENTS]->(el:Incidents)
with lc, el
         match (v:Vehicle{vin:'ZFA3120000JA84963'})
create (v)-[:LAST_INCIDENTS]->(el)

====

match (v:Vehicle{vin:'ZFA3120000JA84963'})-[rel:LAST_TECHNICAL_FAULTS]->(:Faults) delete rel;


match (lc:Lifecycle{uuid:'fdedf3a4-2065-4082-88dd-cb7ef8de393e'})-[:HAVING_TECHNICAL_FAULTS]->(el:Faults)
with lc, el
         match (v:Vehicle{vin:'ZFA3120000JA84963'})
create (v)-[:LAST_TECHNICAL_FAULTS]->(el)




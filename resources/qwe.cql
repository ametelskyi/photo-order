MATCH (v:Vehicle {vin: 'JBPMGPVWXS0947508'}), (lc:Lifecycle {uuid: 'd5624f9a-e352-4aa7-999e-4842e525331b', })
WITH v, lc, lc.state AS state,
     {vehicleCoreData: {unitNumber: v.unitNumber, vin: v.vin, model: v.model, modelCode: v.modelCode, licensePlate: v.
       licensePlate,    carGroup:   v.carGroup, ownerCountry: v.ownerCountry}, vehicleAdditionalData: {vehicleType:      v.
       vehicleType,
                                                                                                       manufacturer:     v.
       manufacturer,
                                                                                                       gearing:          v.
       gearing,
                                                                                                       fuelType:         v.
       fuelType,
                                                                                                       colour:           v.
       color,
                                                                                                       metallic:         v.
       metallic,
                                                                                                       distancePerLiter: v.
       distancePerLiter,
                                                                                                       tankCapacity:     v.
       tankCapacity,
                                                                                                       activationDate:   v.
       activationDate}} AS summary
OPTIONAL MATCH     (lc)-[:HAVING_ACCESSORIES]->(:Accessories)-[:CONTAINING]->(accessories:Accessory)
WITH v, lc, state, summary,
     {accessories: (CASE WHEN accessories IS NOT NULL THEN collect(properties(accessories))
       ELSE null
       END)} AS accessories
OPTIONAL MATCH     (lc)-[:HAVING_EQUIPMENTS]->(:Equipments)-[:CONTAINING]->(equipment:Equipment)
WITH v, lc, state, summary, accessories,
     {equipment: (CASE WHEN equipment IS NOT NULL THEN collect(properties(equipment))
       ELSE null
       END)} AS equipment
OPTIONAL MATCH     (lc)-[:HAVING_TECHNICAL_FAULTS]->(:Faults)-[:CONTAINING]->(faults:Fault)
WITH v, lc, state, summary, accessories, equipment,
     {faults: (CASE WHEN faults IS NOT NULL THEN collect(properties(faults))
       ELSE null
       END)} AS faults
OPTIONAL MATCH    (lc)-[:WAS_IN*]->(wasLc:Lifecycle)
WITH v, lc, state, summary, accessories, equipment, faults, collect(wasLc) + lc AS collectedLcHistory
UNWIND (CASE collectedLcHistory WHEN [] THEN [null]
  ELSE collectedLcHistory
  END)  AS lcHistory
OPTIONAL MATCH (lcHistory)-[:HAVING_DAMAGES]->(:Damages)-[:CONTAINING]->(dAct:Damage)<-[:DETECTED]-(:Detected)
OPTIONAL MATCH (lcHistory)-[:HAVING_DAMAGES]->(:Damages)-[:CONTAINING]->(dRepBefore:Damage)<-[:REPAIRED]-(:Repaired)
  WHERE dRepBefore.repairedTimestamp IS NULL OR  dRepBefore.repairedTimestamp < lc.timestamp
OPTIONAL MATCH (lcHistory)-[:HAVING_DAMAGES]->(:Damages)-[:CONTAINING]->(dRepAfter:Damage)<-[:REPAIRED]-(:Repaired)
  WHERE dRepAfter.repairedTimestamp IS NOT NULL AND dRepAfter.repairedTimestamp >= lc.timestamp
WITH v, lc, state, summary, accessories, equipment, faults, collect(lcHistory) AS tmp1, dAct, dRepBefore, dRepAfter
WITH v, lc, state, summary, accessories, equipment, faults, collect(dAct) + collect(dRepAfter) AS dActiveCollected,
     dRepBefore AS dRepaired, dAct, dRepBefore, dRepAfter
UNWIND (CASE dActiveCollected WHEN [] THEN [null]
  ELSE dActiveCollected
  END)  AS dActive
WITH v, lc, state, summary, accessories, equipment, faults, {damages: collect(properties(dActive))} AS damages,
     {damages: collect(properties(dRepaired))} AS repairedDamages, collect(dActive) AS tmp2, collect(dRepaired) AS tmp3,
     collect(dAct) AS tmp4, collect(dRepBefore) AS tmp5, collect(dRepAfter) AS tmp6
WITH v, lc, state, summary, accessories, equipment, faults, damages, repairedDamages
OPTIONAL MATCH (lc)-[:HAVING_DAMAGES]->(:Damages)-[:CONTAINING]->(damage:Damage)
OPTIONAL MATCH (lc)-[:HAVING_HISTORIC_DAMAGES]->(:Damages)-[:CONTAINING]->(hDamage:Damage)
WITH collect(damage) + collect(hDamage) AS lcDamagesCollected, v, lc, state, summary, accessories, equipment, faults,
     damages, repairedDamages
UNWIND (CASE lcDamagesCollected WHEN [] THEN [null]
  ELSE lcDamagesCollected
  END)  AS lcDms
WITH {damages: collect(properties(lcDms))} AS lcDamages, v, lc, state, summary, accessories, equipment, faults, damages,
     repairedDamages
WITH v, lc, state, summary, accessories, equipment, faults, damages, repairedDamages,
     {damages: (CASE WHEN damage IS NOT NULL THEN collect(properties(damage))
       ELSE []
       END)} AS lcDamages
OPTIONAL MATCH     (lc)-[:HAVING_CONDITIONS]->(:Conditions)-[:CONTAINING]->(fueling:Fueling)
WITH v, lc, state, summary, accessories, equipment, faults, damages, repairedDamages, lcDamages,
     properties(fueling) AS fueling
OPTIONAL MATCH     (lc)-[:HAVING_CONDITIONS]->(:Conditions)-[:CONTAINING]->(mileage:Mileage)
OPTIONAL MATCH (lc)-[:PREVIOUS_MILEAGE]->(prevMile:Mileage)
WITH v, lc, state, summary, accessories, equipment, faults, damages, repairedDamages, lcDamages, fueling,
     properties(mileage) AS mileage, properties(prevMile) AS prevMileage
OPTIONAL MATCH     (lc)-[:LAST_TYRE]->(tyre:Tyre)
WITH v, lc, state, summary, accessories, equipment, faults, damages, repairedDamages, lcDamages, fueling, mileage,
     prevMileage, properties(tyre) AS tyre
OPTIONAL MATCH     (lc)-[:SIGNED_WITH]->(sig:Signature)
WITH v, lc, state, summary, accessories, equipment, faults, damages, repairedDamages, lcDamages, fueling, mileage,
     prevMileage, tyre, properties(sig) AS signature
OPTIONAL MATCH    (v)-[:LAST_PHOTOS]->(ovPht:Photos)
WITH v, lc, state, summary, accessories, equipment, faults, damages, repairedDamages, lcDamages, fueling, mileage,
     prevMileage, tyre, signature, ovPht
OPTIONAL MATCH    (lc)-[:UNDER]->(ra:RA)
WITH v, lc, state, summary, accessories, equipment, faults, damages, repairedDamages, lcDamages, fueling, mileage,
     prevMileage, tyre, signature, ovPht, ra.mileageOut AS tasMileage, properties(ra) AS rentAgr
OPTIONAL MATCH    (lc)<-[:INFLEETS]-(dn:DeliveryNote)
WITH v, lc, state, summary, accessories, equipment, faults, damages, repairedDamages, lcDamages, fueling, mileage,
     prevMileage, tyre, signature, ovPht, tasMileage, rentAgr, properties(dn) AS deliveryNote
OPTIONAL MATCH    (lc)-[:CHECKIN]->(checkin:Checkin)
WITH v, lc, state, summary, accessories, equipment, faults, damages, repairedDamages, lcDamages, fueling, mileage,
     prevMileage, tyre, signature, ovPht, tasMileage, rentAgr, deliveryNote, properties(checkin) AS checkin
OPTIONAL MATCH (lc)-[:CLOSED_FOR]->(closedPrev:Lifecycle)
OPTIONAL MATCH (lc)<-[:CLOSED_FOR]-(closedBy:Lifecycle)
OPTIONAL MATCH (lc)-[:COMPLETED_BY]->(ee:ExternalExecutor)
OPTIONAL MATCH (lc)-[:COMPLETED_BY]->(:ExternalExecutor)-[:PROVIDE]->(ed:ExternalData)
WITH v, lc, state, summary, accessories, equipment, faults, damages, repairedDamages, lcDamages, fueling, mileage,
     prevMileage, tyre, signature, ovPht, tasMileage, rentAgr, deliveryNote, checkin, closedPrev, closedBy,
     ee.name AS externalExecutorName, properties(ed) AS externalExecutorData
RETURN state AS state, summary AS summary, fueling AS fueling, mileage AS mileage, prevMileage AS prevMileage,
       tasMileage AS tasMileage, rentAgr AS ra, tyre AS tyre, accessories AS vehicleAccessories,
       equipment AS vehicleEquipment, faults AS technicalFaults, damages AS vehicleDamages,
       repairedDamages AS repairedDamages, lcDamages AS lcDamages, v.overviewPhoto AS overviewPhoto,
       signature AS signature, lc.documents AS documents, properties(lc) AS lifecycle,
       ovPht.photos AS lastOverviewPhotos, deliveryNote AS deliveryNote, checkin AS checkin,
       closedPrev.uuid AS closedPrev, closedBy.uuid AS closedBy, externalExecutorName AS externalExecutorName,
       externalExecutorData AS externalExecutorData, lc.fnol AS fnol
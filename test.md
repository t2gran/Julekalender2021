# Build configuration

Nnn nn nnn nnnnnnnnn nnnnn nnn Nnnnn nnnnd nnnnrt nnnnnn nn nnnnnnn nn. Nnn nnnn nnnnnn nn
nnnnten nnnn nnnn nnnnnnnny. Nn nnn nnnnnnnny nnnnt, nnn nnnnnnnn nnnns nnn nnnnnn. nn nn nnnn
nnn nnnnn, nn nn nnnnnnn.


## Overview

Nnn nn nnn nnnnnnnnn nnnnn nnn nnnnn nnnnn nnnnnn nnnnnn nn nnnnnn nn.


### Parameters

Nnn nn nn nnnnnnn nnnnn nnn nnnnn, nnn nnnnn nnnnn nnnnnn. Nnn nnnnnn nn nnn nnnnnnnnnnnnnnn
nnn n nnn nnnnnn.

| Parameter                         | Type      | Required / Optional Default Value   | Since | Summary                                       |
|-----------------------------------|-----------|-------------------------------------|-------|-----------------------------------------------|
| areaVisibility                    | `boolean` | `false`                             | 1.5   | Perform visibility calculations on OSM areas. |
| banDiscouragedBiking              | `boolean` | `false`                             | na    | Insert summary here                           |
| banDiscouragedWalking             | `boolean` | `false`                             | na    | Insert summary here                           |
| blockBasedInterlining             | `boolean` | `true`                              | na    | Insert summary here                           |
| buildReportDir                    | `uri`     |                                     | na    | Insert summary here                           |
| configVersion                     | `string`  |                                     | na    | Insert summary here                           |
| dataImportReport                  | `boolean` | `false`                             | na    | Insert summary here                           |
| dataOverlay                       | `object`  |                                     | na    | Insert summary here                           |
| dem                               | `object`  |                                     | na    | Insert summary here                           |
| discardMinTransferTimes           | `boolean` | `false`                             | na    | Insert summary here                           |
| distanceBetweenElevationSamples   | `double`  | `10.0`                              | na    | Insert summary here                           |
| elevationBucket                   | `object`  |                                     | na    | Insert summary here                           |
|     accessKey                     | `string`  | *Required*                          | na    | Insert summary here                           |
|     bucketName                    | `string`  | *Required*                          | na    | Insert summary here                           |
|     secretKey                     | `string`  | *Required*                          | na    | Insert summary here                           |
| elevationUnitMultiplier           | `double`  | `1.0`                               | na    | Insert summary here                           |
| embedRouterConfig                 | `boolean` | `true`                              | na    | Insert summary here                           |
| extraEdgesStopPlatformLink        | `boolean` | `false`                             | na    | Insert summary here                           |
| graph                             | `uri`     |                                     | na    | Insert summary here                           |
| gsCredentials                     | `string`  |                                     | na    | Insert summary here                           |
| includeEllipsoidToGeoidDifference | `boolean` | `false`                             | na    | Insert summary here                           |
| islandWithStopsMaxSize            | `integer` | `5`                                 | na    | Insert summary here                           |
| islandWithoutStopsMaxSize         | `integer` | `40`                                | na    | Insert summary here                           |
| localFileNamePatterns             | `object`  |                                     | na    | Insert summary here                           |
|     dem                           | `regexp`  | `"(?i)\.tiff?$"`                    | na    | Insert summary here                           |
|     gtfs                          | `regexp`  | `"(?i)gtfs"`                        | na    | Insert summary here                           |
|     netex                         | `regexp`  | `"(?i)netex"`                       | na    | Insert summary here                           |
|     osm                           | `regexp`  | `"(?i)(\.pbf\|\.osm\|\.osm\.xml)$"` | na    | Insert summary here                           |
| matchBusRoutesToStreets           | `boolean` | `false`                             | na    | Insert summary here                           |
| maxAreaNodes                      | `integer` | `500`                               | na    | Insert summary here                           |
| maxDataImportIssuesPerFile        | `integer` | `1000`                              | na    | Insert summary here                           |
| maxElevationPropagationMeters     | `integer` | `2000`                              | na    | Insert summary here                           |
| maxInterlineDistance              | `integer` | `200`                               | na    | Insert summary here                           |
| maxStopToShapeSnapDistance        | `double`  | `150.0`                             | na    | Insert summary here                           |
| maxTransferDurationSeconds        | `double`  | `1800.0`                            | na    | Insert summary here                           |
| multiThreadElevationCalculations  | `boolean` | `false`                             | na    | Insert summary here                           |
| netexDefaults                     | `object`  |                                     | na    | Insert summary here                           |
|     groupFilePattern              | `regexp`  | `"(\w{3})-.*\.xml"`                 | na    | Insert summary here                           |
|     ignoreFilePattern             | `regexp`  | `"$^"`                              | na    | Insert summary here                           |
|     netexFeedId                   | `string`  | `"DefaultFeed"`                     | na    | Insert summary here                           |
|     sharedFilePattern             | `regexp`  | `"shared-data\.xml"`                | na    | Insert summary here                           |
|     sharedGroupFilePattern        | `regexp`  | `"(\w{3})-.*-shared\.xml"`          | na    | Insert summary here                           |
| osm                               | `object`  |                                     | na    | Insert summary here                           |
| osmCacheDataInMem                 | `boolean` | `false`                             | na    | Insert summary here                           |
| osmDefaults                       | `object`  |                                     | na    | Insert summary here                           |
|     osmTagMapping                 | `string`  | `"default"`                         | na    | Insert summary here                           |
| platformEntriesLinking            | `boolean` | `false`                             | na    | Insert summary here                           |
| readCachedElevations              | `boolean` | `true`                              | na    | Insert summary here                           |
| staticBikeParkAndRide             | `boolean` | `false`                             | na    | Insert summary here                           |
| staticParkAndRide                 | `boolean` | `true`                              | na    | Insert summary here                           |
| streetGraph                       | `uri`     |                                     | na    | Insert summary here                           |
| streets                           | `boolean` | `true`                              | na    | Insert summary here                           |
| subwayAccessTime                  | `double`  | `2.0`                               | na    | Insert summary here                           |
| transferRequests                  | `object`  |                                     | na    | Insert summary here                           |
| transit                           | `boolean` | `true`                              | na    | Insert summary here                           |
| transitFeeds                      | `object`  |                                     | na    | Insert summary here                           |
| transitServiceEnd                 | `string`  | `"P3Y"`                             | na    | Insert summary here                           |
| transitServiceStart               | `string`  | `"-P1Y"`                            | na    | Insert summary here                           |
| writeCachedElevations             | `boolean` | `false`                             | na    | Insert summary here                           |


### Deprecated Parameters

The list of old parameters below are parameters that exist in earlier versions of OTP v2.x.
The since `2.1 (1.5)` mean that this parameter existed in all versions from 1.5 an up until
version 2.1 - where the parameter was removed. The Description should give you a hint on how
to migrate to the new version.

| Parameter | Since    | Deprecation Summary               | Type     | Original Summary                 |
|-----------|----------|-----------------------------------|----------|----------------------------------|
| reportDir | 2.1(1.5) | Use the `buildReportDir` instead. | `string` | The location of the issue report |


## Parameter Details

This section explain some of the parameters in more detail. Not all parameters can be
explained with one sentence, so we document them here. The summary section above list ALL
parameters, this section only list those with a detail description. Link to [test](#Overview)

### Test2


#### areaVisibility

*Since version 1.5 ∙ Type: `boolean` ∙ Optional  ∙ Default vaule: `false`*

Perform visibility calculations on OSM areas. These calculations can be time consuming.


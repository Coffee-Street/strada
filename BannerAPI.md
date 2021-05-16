## STRADA API 명세서




## Banners API

	STRADA 앱의 메인 화면 banner 정보를 API 서버에 요청한다.
 
## APIs

1. http://{Server URL}:8080/strada/v1/coffees/banners/{id}
    * coffee의 배너 정보를 서버에 요청
    * Method: GET
    * Parameter:
    
    ||Parameter|type|Max Size|
    |------|---|---|---|
    |id|String|25|
    
    * Response: 200 - success
    
    ||Param|Type|Description|
    |------|---|---|---|
    |id|String|요청한 coffee의 이름|
    |url|String|요청한 coffee의 이미지 주소|
    |description|String|요청한 coffee의 설명|
        
    example
    ```
    {
      "id": "string",
      "url": "string",
      "description": "string"
    }
    ```
    * Error: 404 - Not found exception

  
2. http://{Server URL}:8080/strada/v1/noncoffees/banners/{id}
    * Non coffee의 배너 정보를 서버에 요청
    * Method: GET
    * Parameter:
    
    ||Parameter|type|Max Size|
    |------|---|---|---|
    |id|String|25|
    
    * Response: 200 - success
    
    ||Param|Type|Description|
    |------|---|---|---|
    |id|String|요청한 noncoffees 이름|
    |url|String|요청한 noncoffees 이미지 주소|
    |description|String|요청한 noncoffees 설명|
        
    example
    ```
    {
      "id": "string",
      "url": "string",
      "description": "string"
    }
    ```
    * Error: 404 - Not found exception

  
3. http://{Server URL}:8080/strada/v1/breads/banners/{id}
    * Bread의 배너 정보를 서버에 요청
    * Method: GET
    * Parameter:
    
    ||Parameter|type|Max Size|
    |------|---|---|---|
    |id|String|25|
    
    * Response: 200 - success
    
    ||Param|Type|Description|
    |------|---|---|---|
    |id|String|요청한 bread의 이름|
    |url|String|요청한 bread의 이미지 주소|
    |description|String|요청한 bread의 설명|
        
    example
    ```
    {
      "id": "string",
      "url": "string",
      "description": "string"
    }
    ```
    * Error: 404 - Not found exception


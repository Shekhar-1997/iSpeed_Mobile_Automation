package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.*;
import tests.BaseTest;
import utils.DemoAPI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeleteAllStatusListDB extends BaseTest {

    private static DemoAPI demoAPI = new DemoAPI();
    // private static List<String> accDeviceCtrlNos = new ArrayList<>();
    private static List<Integer> noticeIds = new ArrayList<>();


    public static void captureDBEvidenceDeleteAll(String nameOfMethod,String alertType, Boolean valiDate) {
            getAlertDbDBData(nameOfMethod, alertType,valiDate);

    }

    public static void getAlertDbDBData(String sheetName, String alertType, Boolean valiDate) {
        String noticeIdsStr = noticeIds.toString().replace("[", "(").replace("]", ")");
        System.out.println("The noticeids: " + noticeIdsStr);
        String requestBody = "{\n" +
                "  \"query\": \"SELECT BRANCH_CD, CLIENT_CD, NOTICE_CTRL_NO, NOTICE_KBN, PRDCT_CD, SEC_TYP_CD, CONTRACT_CD, DSCR_CD, TICKER, DSCR_NM,        MARKET_CD, INDICES_CD, INDICES_NM, PRICE_KBN, VALUE, VALUE_KBN, SEND_MESSAGE, CA_INFO_KBN, NOTICE_OCCURENCE_DT,        SEND_TRD_KBN, EXEC_NOMINAL, EXEC_PRC FROM (    -- First subquery    SELECT C.BRANCH_CD, C.CLIENT_CD, C.NOTICE_CTRL_NO, C.NOTICE_KBN, D.PRDCT_CD, D.SEC_TYP_CD, D.CONTRACT_CD, D.DSCR_CD,            D.TICKER, D.DSCR_NM, D.MARKET_CD, D.INDICES_CD, D.INDICES_NM, D.PRICE_KBN, D.VALUE, D.VALUE_KBN,            C.SEND_MESSAGE, D.CA_INFO_KBN, C.NOTICE_OCCURENCE_DT, '' AS SEND_TRD_KBN, 0 AS EXEC_NOMINAL, 0 AS EXEC_PRC    FROM (        SELECT A.NOTICE_CTRL_NO, A.BRANCH_CD, A.CLIENT_CD, A.NOTICE_ID, B.NOTICE_OCCURENCE_DT, B.SEND_MESSAGE,                B.NOTICE_KBN, ROW_NUMBER() OVER (PARTITION BY A.BRANCH_CD, A.CLIENT_CD, B.NOTICE_CTRL_NO,                B.NOTICE_OCCURENCE_DT ORDER BY B.NOTICE_OCCURENCE_DT, A.BRANCH_CD, A.CLIENT_CD, B.NOTICE_CTRL_NO) AS RNUMBER        FROM PUSH_NOTICE_SERVICE_INFO A        JOIN PUSH_NOTICE_LOG_INFO B ON A.NOTICE_CTRL_NO = B.NOTICE_CTRL_NO        WHERE B.NOTICE_STS = '1'           AND B.DISPLAY_FLG = '1'           AND B.NOTICE_KBN IN ('1', '2', '3', '4', '5', '11', '12', '13', '14', '15', '18')           AND A.BRANCH_CD = '6DIyBb3pBWV237RUlYutNw=='           AND A.CLIENT_CD = '5atY1axLk8fE1hkzJ+6iQg=='    ) C    JOIN PUSH_NOTICE_SET_INFO D ON C.NOTICE_ID = D.NOTICE_ID    WHERE C.RNUMBER = 1    UNION ALL    -- Second subquery    SELECT E.BRANCH_CD, E.CLIENT_CD, E.NOTICE_CTRL_NO, E.NOTICE_KBN, '' AS PRDCT_CD, '' AS SEC_TYP_CD,            '' AS CONTRACT_CD, E.DSCR_CD, '' AS TICKER, E.DSCR_NM, CAST(E.MARKET_CD AS CHAR) AS MARKET_CD,            '' AS INDICES_CD, '' AS INDICES_NM, '' AS PRICE_KBN, '' AS VALUE, '' AS VALUE_KBN, '' AS SEND_MESSAGE,            '' AS CA_INFO_KBN, E.NOTICE_OCCURENCE_DT, CAST(E.SEND_TRD_KBN AS CHAR) AS SEND_TRD_KBN,            E.EXEC_NOMINAL, E.EXEC_PRC    FROM (        SELECT NOTICE_CTRL_NO, SEND_TRD_KBN, BRANCH_CD, CLIENT_CD, DSCR_CD, DSCR_NM, MARKET_CD, EXEC_NOMINAL,                EXEC_PRC, NOTICE_KBN, NOTICE_OCCURENCE_DT,                ROW_NUMBER() OVER (PARTITION BY NOTICE_CTRL_NO, NOTICE_OCCURENCE_DT                                   ORDER BY NOTICE_OCCURENCE_DT, NOTICE_CTRL_NO) AS RNUMBER2        FROM (            SELECT F.NOTICE_CTRL_NO, G.NOTICE_OCCURENCE_DT, F.SEND_TRD_KBN, F.BRANCH_CD, F.CLIENT_CD,                    F.DSCR_CD, F.DSCR_NM, F.MARKET_CD, F.EXEC_NOMINAL, F.EXEC_PRC, G.NOTICE_KBN            FROM EXEC_NOTICE_TMP_INFO F            JOIN PUSH_NOTICE_LOG_INFO G ON F.NOTICE_CTRL_NO = G.NOTICE_CTRL_NO            WHERE F.BRANCH_CD = '6DIyBb3pBWV237RUlYutNw=='               AND F.CLIENT_CD = '5atY1axLk8fE1hkzJ+6iQg=='               AND G.NOTICE_STS = '1'               AND G.DISPLAY_FLG = '1'               AND G.NOTICE_KBN = '7'                        UNION ALL            SELECT H.NOTICE_CTRL_NO, I.NOTICE_OCCURENCE_DT, H.SEND_TRD_KBN, H.BRANCH_CD, H.CLIENT_CD,                    H.DSCR_CD, H.DSCR_NM, H.MARKET_CD, H.EXEC_NOMINAL, H.EXEC_PRC, I.NOTICE_KBN            FROM EXEC_NOTICE_TMP_INFO_HIST H            JOIN PUSH_NOTICE_LOG_INFO I ON H.NOTICE_CTRL_NO = I.NOTICE_CTRL_NO            WHERE H.BRANCH_CD = '6DIyBb3pBWV237RUlYutNw=='               AND H.CLIENT_CD = '5atY1axLk8fE1hkzJ+6iQg=='               AND I.NOTICE_STS = '1'               AND I.DISPLAY_FLG = '1'               AND I.NOTICE_KBN = '7'        ) ALIAS1    ) E    WHERE E.RNUMBER2 = 1    UNION ALL    -- Third subquery    SELECT J.BRANCH_CD, J.CLIENT_CD, J.NOTICE_CTRL_NO, J.NOTICE_KBN, '' AS PRDCT_CD, '' AS SEC_TYP_CD,            '' AS CONTRACT_CD, '' AS DSCR_CD, J.TICKER, J.DSCR_NM, CAST(J.MARKET_CD AS CHAR) AS MARKET_CD,            '' AS INDICES_CD, '' AS INDICES_NM, '' AS PRICE_KBN, '' AS VALUE, '' AS VALUE_KBN, '' AS SEND_MESSAGE,            '' AS CA_INFO_KBN, J.NOTICE_OCCURENCE_DT, CAST(J.SEND_TRD_KBN AS CHAR) AS SEND_TRD_KBN,            J.EXEC_NOMINAL, J.EXEC_PRC    FROM (        SELECT NOTICE_CTRL_NO, SEND_TRD_KBN, BRANCH_CD, CLIENT_CD, TICKER, DSCR_NM, MARKET_CD, EXEC_NOMINAL,                EXEC_PRC, NOTICE_KBN, NOTICE_OCCURENCE_DT,                ROW_NUMBER() OVER (PARTITION BY NOTICE_CTRL_NO, NOTICE_OCCURENCE_DT                                   ORDER BY NOTICE_OCCURENCE_DT, NOTICE_CTRL_NO) AS RNUMBER3        FROM (            SELECT K.NOTICE_CTRL_NO, L.NOTICE_OCCURENCE_DT, K.SEND_TRD_KBN, K.BRANCH_CD, K.CLIENT_CD,                    K.TICKER, K.DSCR_NM, K.MARKET_CD, K.EXEC_NOMINAL, K.EXEC_PRC, L.NOTICE_KBN            FROM US_EXEC_TMP_INFO K            JOIN PUSH_NOTICE_LOG_INFO L ON K.NOTICE_CTRL_NO = L.NOTICE_CTRL_NO            WHERE K.BRANCH_CD = '6DIyBb3pBWV237RUlYutNw=='               AND K.CLIENT_CD = '5atY1axLk8fE1hkzJ+6iQg=='               AND L.NOTICE_STS = '1'               AND L.DISPLAY_FLG = '1'               AND L.NOTICE_KBN = '17'            UNION ALL            SELECT M.NOTICE_CTRL_NO, N.NOTICE_OCCURENCE_DT, M.SEND_TRD_KBN, M.BRANCH_CD, M.CLIENT_CD,                    M.TICKER, M.DSCR_NM, M.MARKET_CD, M.EXEC_NOMINAL, M.EXEC_PRC, N.NOTICE_KBN            FROM US_EXEC_TMP_INFO_HIST M            JOIN PUSH_NOTICE_LOG_INFO N ON M.NOTICE_CTRL_NO = N.NOTICE_CTRL_NO            WHERE M.BRANCH_CD = '6DIyBb3pBWV237RUlYutNw=='               AND M.CLIENT_CD = '5atY1axLk8fE1hkzJ+6iQg=='               AND N.NOTICE_STS = '1'               AND N.DISPLAY_FLG = '1'               AND N.NOTICE_KBN = '17'        ) ALIAS2    ) J    WHERE J.RNUMBER3 = 1    UNION ALL    -- Fourth subquery (added block)    SELECT O.BRANCH_CD, O.CLIENT_CD, O.NOTICE_CTRL_NO, O.NOTICE_KBN, '' AS PRDCT_CD, '' AS SEC_TYP_CD,            '' AS CONTRACT_CD, '' AS DSCR_CD, '' AS TICKER, '' AS DSCR_NM, '' AS MARKET_CD, '' AS INDICES_CD,            '' AS INDICES_NM, '' AS PRICE_KBN, '' AS VALUE, '' AS VALUE_KBN, '' AS SEND_MESSAGE,            '' AS CA_INFO_KBN, O.NOTICE_OCCURENCE_DT, '' AS SEND_TRD_KBN, 0 AS EXEC_NOMINAL, 0 AS EXEC_PRC    FROM (        SELECT NOTICE_CTRL_NO, BRANCH_CD, CLIENT_CD, NOTICE_KBN, NOTICE_OCCURENCE_DT,                ROW_NUMBER() OVER (PARTITION BY NOTICE_CTRL_NO, NOTICE_OCCURENCE_DT                                   ORDER BY NOTICE_OCCURENCE_DT, NOTICE_CTRL_NO) AS RNUMBER4        FROM (            SELECT P.NOTICE_CTRL_NO, Q.NOTICE_OCCURENCE_DT, P.BRANCH_CD, P.CLIENT_CD, Q.NOTICE_KBN            FROM MGN_LOSSCUT_TMP_INFO P            JOIN PUSH_NOTICE_LOG_INFO Q ON P.NOTICE_CTRL_NO = Q.NOTICE_CTRL_NO            WHERE P.BRANCH_CD = '6DIyBb3pBWV237RUlYutNw=='               AND P.CLIENT_CD = '5atY1axLk8fE1hkzJ+6iQg=='               AND Q.NOTICE_STS = '1'               AND Q.DISPLAY_FLG = '1'               AND Q.NOTICE_KBN IN ('28', '29')            UNION ALL            SELECT R.NOTICE_CTRL_NO, S.NOTICE_OCCURENCE_DT, R.BRANCH_CD, R.CLIENT_CD, S.NOTICE_KBN            FROM MGN_LOSSCUT_TMP_INFO_HIST R            JOIN PUSH_NOTICE_LOG_INFO S ON R.NOTICE_CTRL_NO = S.NOTICE_CTRL_NO            WHERE R.BRANCH_CD = '6DIyBb3pBWV237RUlYutNw=='               AND R.CLIENT_CD = '5atY1axLk8fE1hkzJ+6iQg=='               AND S.NOTICE_STS = '1'               AND S.DISPLAY_FLG = '1'               AND S.NOTICE_KBN IN ('28', '29')        ) ALIAS3    ) O    WHERE O.RNUMBER4 = 1) ALIAS4ORDER BY NOTICE_OCCURENCE_DT DESC,         CASE             WHEN NOTICE_KBN = '1' THEN 1            WHEN NOTICE_KBN = '3' THEN 2            WHEN NOTICE_KBN = '2' THEN 3            WHEN NOTICE_KBN = '4' THEN 4            WHEN NOTICE_KBN = '5' THEN 5            WHEN NOTICE_KBN = '7' THEN 6            WHEN NOTICE_KBN = '11' THEN 7            WHEN NOTICE_KBN = '13' THEN 8            WHEN NOTICE_KBN = '18' THEN 9            WHEN NOTICE_KBN = '12' THEN 10            WHEN NOTICE_KBN = '14' THEN 11            WHEN NOTICE_KBN = '15' THEN 12            WHEN NOTICE_KBN = '17' THEN 13            WHEN NOTICE_KBN = '28' THEN 14            WHEN NOTICE_KBN = '29' THEN 15         END,          DSCR_CD, TICKER, MARKET_CD, INDICES_CD, PRICE_KBN\"\n" +
                "}";

        System.out.println("the request body:" + requestBody);
        noticeIds.clear();

        try {
            Response response = demoAPI.getValueFromPushDB(requestBody);
            String responseBody = response.getBody().asString();
            JsonPath jsonPath = new JsonPath(responseBody);

            System.out.println("Response Body: " + responseBody);

            List<Map<String, Object>> dataList = jsonPath.getList("data");
            if (dataList == null) {
                System.out.println("Data list is null. Response body:");
                System.out.println(responseBody);
                return;
            }

            // Get or create the sheet
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }

            // Calculate row number for new data
            int rowNum = sheet.getLastRowNum() + 3;

            // Insert the sheet name above the headers
            Row sheetNameRow = sheet.createRow(rowNum++);
            Cell sheetNameCell = sheetNameRow.createCell(0);
            sheetNameCell.setCellValue(alertType);

            // Add headers
            String[] headers = { "branch_cd","client_cd", "notice_ctrl_no", "notice_kbn", "prdct_cd", "sec_typ_cd", "contract_cd", "dscr_cd", "ticker", "dscr_nm", "market_cd", "indices_cd", "indices_nm", "price_kbn", "value", "value_kbn", "send_message", "ca_info_kbn", "notice_occurence_dt", "send_trd_kbn", "exec_nominal", "exec_prc"};

            Row headerRow = sheet.createRow(rowNum++);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            //Add data rows and check notice_occurence_kbn
            for (Map<String, Object> data : dataList) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = row.createCell(i);
                    Object value = data.get(headers[i]);
                    if (value != null) {
                        cell.setCellValue(value.toString());
                    } else {
                        cell.setCellValue("");
                    }
                }

                // Compare notice_occurence_kbn and print message
                if(valiDate) {
                    String noticeOccurenceKbn = data.get("notice_occurence_kbn").toString();
                    Row messageRow = sheet.createRow(rowNum++);
                    Cell messageCell = messageRow.createCell(0);
                    if ("0".equals(noticeOccurenceKbn)) {
                        messageCell.setCellValue("alert not triggered");
                    } else if ("1".equals(noticeOccurenceKbn)) {
                        messageCell.setCellValue("alert triggered");
                    }
                }
            }

            // Write changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the Excel file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }


}




package com.erisasadikllari.tbu_tiranabank.services;

import com.erisasadikllari.tbu_tiranabank.models.Account;
import com.erisasadikllari.tbu_tiranabank.models.AccountType;
import com.erisasadikllari.tbu_tiranabank.models.Transaction;
import com.erisasadikllari.tbu_tiranabank.repositories.AccountRepository;
import com.erisasadikllari.tbu_tiranabank.repositories.TransactionRepository;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;


    public Transaction createTransaction(Transaction transaction){
        Optional<Account> account=accountRepository.findById(transaction.getDebitAccount().getId());
        Optional<Account> creditAccountOpt=accountRepository.findByAccountNumber(transaction.getCreditAccount());
        if (creditAccountOpt.isEmpty()){
            throw new IllegalArgumentException("Credit Account Number does not exist!");
        }
        Account creditAccount=creditAccountOpt.get();
        Account debitAccount=account.get();

        if (debitAccount.getAccountNumber().equals(creditAccount.getAccountNumber())){
            throw new IllegalArgumentException("You can not do transactions at the same Debit and Credit Account!");
        }

        if (!debitAccount.getCurrency().equals(creditAccount.getCurrency())){
            throw new IllegalArgumentException("Debit and Credit Account must have the same currency!");
        }

        if (debitAccount.getAccount_type().equals(AccountType.Loan)) {
            throw new IllegalArgumentException("You cannot debit a loan account!");
        }
        double amount=transaction.getAmount();
        if (debitAccount.getBalance()<amount){
            throw new IllegalArgumentException("This account does not have sufficient balance!");
        }

        debitAccount.setBalance(debitAccount.getBalance()-amount);
        creditAccount.setBalance(creditAccount.getBalance()+amount);

        accountRepository.save(debitAccount);
        accountRepository.save(creditAccount);

        transaction.setDebitAccount(debitAccount);
        transaction.setCreditAccount(creditAccount.getAccountNumber());

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactionsByDebitAccountId(Long id){
        return transactionRepository.findAllByDebitAccountId(id);
    }

    public List<Transaction> getAllTransactionsByCreditAccountNumber(String creditAccountNumber){
        return transactionRepository.findAllByCreditAccountContains(creditAccountNumber);
    }


    public List<Transaction> getAllTransactionsByDebitIdBetweenDate(Long id,Date startDate, Date endDate){
        return transactionRepository.findTransactionsByDebitAccountIdAndTransactionDateBetween(id, startDate,endDate);
    }

    public List<Transaction> getAllTransactionsByCreditIdBetweenDate(String accountNumber,Date startDate, Date endDate){
        return transactionRepository.findTransactionsByCreditAccountAndTransactionDateBetween(accountNumber, startDate,endDate);
    }

//    public byte[] generatePDF() throws IOException {
//        List<Transaction> transactions = transactionRepository.findAll(); // Adjust based on your repository method
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        PDDocument pdfDocument = new PDDocument();
//
//        try {
//            PDPage page = new PDPage();
//            pdfDocument.addPage(page);
//
//            PDPageContentStream contentStream = new PDPageContentStream(pdfDocument, page);
//            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
//            contentStream.beginText();
//            contentStream.newLineAtOffset(100, 700);
//
//            for (Transaction transaction : transactions) {
//                contentStream.showText("Transaction ID: " + transaction.getId());
//                contentStream.newLine();
//                contentStream.showText("Amount: " + transaction.getAmount());
//                contentStream.newLine();
//                contentStream.newLineAtOffset(60, -40);
//            }
//
//            contentStream.endText();
//            contentStream.close();
//
//            pdfDocument.save(baos);
//            pdfDocument.close();
//
//            return baos.toByteArray();
//        } finally {
//            if (pdfDocument != null) {
//                pdfDocument.close();
//            }
//            if (baos != null) {
//                baos.close();
//            }
//        }
//    }

public byte[] generatePDF(List<Transaction> debitTransactions, List<Transaction> creditTransactions) throws IOException {

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PDDocument pdfDocument = new PDDocument();

    try {
        PDPage page = new PDPage();
        pdfDocument.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(pdfDocument, page);
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

        float margin = 20;
        float yStart = page.getMediaBox().getHeight() - margin;
        float yPosition = yStart;
        float cellMargin = 10f;
        float fontSize = 12f;

        float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
        float[] columnWidths = {tableWidth * 0.2f, tableWidth * 0.25f, tableWidth * 0.2f, tableWidth * 0.2f, tableWidth * 0.15f};

        drawTableHeader(contentStream, yPosition, margin, fontSize, columnWidths);
        yPosition -= fontSize + cellMargin;

        for (Transaction transaction : debitTransactions) {
            drawTableRow(contentStream, transaction, yPosition, margin, fontSize, columnWidths);
            yPosition -= fontSize + cellMargin;
        }

        for (Transaction transaction : creditTransactions) {
            drawTableRow(contentStream, transaction, yPosition, margin, fontSize, columnWidths);
            yPosition -= fontSize + cellMargin;
        }

        contentStream.close();

        pdfDocument.save(baos);
        pdfDocument.close();

        return baos.toByteArray();
    } finally {
        if (pdfDocument != null) {
            pdfDocument.close();
        }
        if (baos != null) {
            baos.close();
        }
    }
}

    private void drawTableHeader(PDPageContentStream contentStream, float yPosition, float margin, float fontSize, float[] columnWidths) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, fontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, yPosition);

        String[] headers = {"Transaction Date", "Description", "Debit Account", "Credit Account", "Amount"};
        float currentX = margin;

        for (int i = 0; i < headers.length; i++) {
            contentStream.showText(headers[i]);
            currentX += columnWidths[i];
            contentStream.newLineAtOffset(columnWidths[i], 0);
        }

        contentStream.endText();
    }

    private void drawTableRow(PDPageContentStream contentStream, Transaction transaction, float yPosition, float margin, float fontSize, float[] columnWidths) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA, fontSize);

        float rowHeight = fontSize + 2;
        float currentY = yPosition;
        float currentX = margin;

        String[] rowData = {
                transaction.getTransactionDate().getDate()+"/"+(transaction.getTransactionDate().getMonth()+1)+"/"+(transaction.getTransactionDate().getYear()+1900),
                transaction.getDescription(),
                transaction.getDebitAccount().getAccountNumber(),
                transaction.getCreditAccount(),
                (transaction.getAmount() )+ " "+
                (transaction.getDebitAccount().getCurrency())
        };

        for (int i = 0; i < rowData.length; i++) {
            String cellContent = rowData[i];
            List<String> lines = splitTextToFit(cellContent, columnWidths[i]);

            for (String line : lines) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, fontSize);
                contentStream.newLineAtOffset(currentX, currentY);
                contentStream.showText(line);
                contentStream.endText();
                currentY -= rowHeight;
            }

            currentX += columnWidths[i];
            currentY = yPosition;
        }
    }

    private List<String> splitTextToFit(String text, float maxWidth) throws IOException {
        List<String> lines = new ArrayList<>();
        int lastSpace = -1;

        while (text.length() > 0) {
            int spaceIndex = text.indexOf(' ', lastSpace + 1);
            if (spaceIndex < 0)
                spaceIndex = text.length();

            String subString = text.substring(0, spaceIndex);
            float stringWidth = PDType1Font.HELVETICA.getStringWidth(subString) / 1000 * 12;

            if (stringWidth > maxWidth) {
                if (lastSpace < 0)
                    lastSpace = spaceIndex;
                subString = text.substring(0, lastSpace);
                lines.add(subString);
                text = text.substring(lastSpace).trim();
                lastSpace = -1;
            } else if (spaceIndex == text.length()) {
                lines.add(text);
                text = "";
            } else {
                lastSpace = spaceIndex;
            }
        }

        return lines;
    }
    public byte[] generateCSV(List<Transaction> debitTransactions, List<Transaction> creditTransactions) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             OutputStreamWriter writer = new OutputStreamWriter(baos)) {

            writer.append("Transaction Date ");
            writer.append("Description  ");
            writer.append(" Debit Account  ");
            writer.append(" Credit Account  ");
            writer.append("Amount\n");

            for (Transaction transaction : debitTransactions) {
                writer.append(transaction.getTransactionDate().getDate()+"/"+(transaction.getTransactionDate().getMonth()+1)+"/"+(transaction.getTransactionDate().getYear()+1900)).append(",");
                writer.append(String.valueOf(transaction.getDescription())).append(",");
                writer.append(String.valueOf(transaction.getDebitAccount().getAccountNumber())).append(",");
                writer.append(String.valueOf(transaction.getCreditAccount())).append(",");
                writer.append(String.valueOf(transaction.getAmount())).append("\n");
            }

            for (Transaction transaction : creditTransactions) {
                writer.append(transaction.getTransactionDate().getDate()+"/"+(transaction.getTransactionDate().getMonth()+1)+"/"+(transaction.getTransactionDate().getYear()+1900)).append(",");
                writer.append(String.valueOf(transaction.getDescription())).append(",");
                writer.append(String.valueOf(transaction.getDebitAccount().getAccountNumber())).append(",");
                writer.append(String.valueOf(transaction.getCreditAccount())).append(",");
                writer.append(String.valueOf(transaction.getAmount())).append("\n");
            }
            writer.flush();
            return baos.toByteArray();
        }
    }
}
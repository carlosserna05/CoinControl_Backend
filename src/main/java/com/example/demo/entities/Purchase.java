package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="purchases")

public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private Customer customer;
    private LocalDate date;
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private TypeCredit typeCredit;
    @Enumerated(EnumType.STRING)
    private SalesStatus salesStatus;
    //private List<PurchaseDetail> purchaseDetails;

    public enum TypeCredit{
        VALOR_FUTURO,
        ANUALIDAD_SIMPLE_VENCIDA
    }

    public enum SalesStatus {
        PENDIENTE,
        PAGADO
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "purchase")
    private List<PurchaseDetail> purchaseDetails;


    /*
    @ManyToOne
    @JoinColumn(name = "purchase_Id")
    private Purchase purchase;
*/
}

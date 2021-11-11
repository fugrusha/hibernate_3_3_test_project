package com.example;

import com.example.config.HibernateUtil;
import com.example.domain.Recipient;
import com.example.domain.Requisite;
import com.example.dto.RecipientAndRequisiteDTO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;

import java.util.List;

public class Main {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = HibernateUtil.getSessionFactory();

        insertData();

        System.out.println("Reading from DB:");
        List<Recipient> recipients = readData();
        recipients.forEach(System.out::println);

        System.out.println("Reading from DB with JOIN:");
        List<RecipientAndRequisiteDTO> leftJoinResult = readDataWithJoin();
        leftJoinResult.forEach(System.out::println);

        HibernateUtil.shutdown();
    }

    private static List<Recipient> readData() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Recipient.class)
                .setProjection(Projections.projectionList()
                        .add(Projections.property("id"), "id")
                        .add(Projections.property("name"), "name")
                ).setResultTransformer(Transformers.aliasToBean(Recipient.class));

        List<Recipient> recipients = criteria.list();

        session.getTransaction().commit();
        session.close();

        return recipients;
    }

    private static List<RecipientAndRequisiteDTO> readDataWithJoin() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria recipientCriteria = session.createCriteria(Recipient.class, "recipient");
        recipientCriteria.createCriteria("requisites", "req");

        recipientCriteria.setProjection(Projections.projectionList()
                .add(Projections.property("id"))
                .add(Projections.property("name"))
                .add(Projections.property("req.note"))
        );

        // fixme write your own ResultTransformer
        recipientCriteria.setResultTransformer(
                new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return new RecipientAndRequisiteDTO(
                                ((Number) objects[0]).longValue(),
                                objects[1].toString(),
                                objects[2].toString()
                        );
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                }
        );

        List<RecipientAndRequisiteDTO> recipients = recipientCriteria.list();

        session.getTransaction().commit();
        session.close();

        return recipients;
    }

    private static void insertData() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Recipient recipient1 = new Recipient("46879", "Simon");
        Recipient recipient2 = new Recipient("99999", "Mark");
        session.save(recipient1);
        session.save(recipient2);

        Requisite req1 = new Requisite("111", "bill_01", "note_01", recipient1);
        Requisite req2 = new Requisite("222", "bill_02", "note_02", recipient1);
        Requisite req3 = new Requisite("333", "bill_01", "note_01", recipient1);
        session.save(req1);
        session.save(req2);
        session.save(req3);

        session.getTransaction().commit();

        session.close();
    }
}

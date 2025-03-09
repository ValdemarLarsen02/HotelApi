package app.service;

import app.model.Hotel;
import app.model.Room;
import app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class HotelServiceImpl implements HotelService {
    @Override
    public List<Hotel> getAllHotels() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Hotel", Hotel.class).list();
        }
    }

    @Override
    public Hotel getHotelById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Hotel.class, id);
        }
    }

    @Override
    public void createHotel(Hotel hotel) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Debugging prints
            System.out.println("🚀 Modtaget request til at oprette hotel: " + hotel);
            if (hotel.getName() == null || hotel.getAddress() == null) {
                System.err.println("❌ Fejl: Hotel name eller address er null!");
                throw new IllegalArgumentException("Hotel name og address må ikke være null.");
            }

            session.persist(hotel);
            transaction.commit();

            System.out.println("✅ Hotel gemt i databasen: " + hotel.getId());

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            System.err.println("❌ Fejl ved oprettelse af hotel: " + e.getMessage());
        }
    }



    @Override
    public void updateHotel(Hotel hotel) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(hotel);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteHotel(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Hotel hotel = session.get(Hotel.class, id);
            if (hotel != null) {
                session.remove(hotel);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void addRoom(Hotel hotel, Room room) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            room.setHotel(hotel); // Tildel værelset til hotellet

            // ✅ Brug `merge()` i stedet for `persist()`, hvis rummet allerede har en ID
            session.merge(room);

            transaction.commit(); // ✅ Commit ændringerne
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }





    @Override
    public void removeRoom(Hotel hotel, Room room) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Find rummet i databasen
            Room existingRoom = session.get(Room.class, room.getId());
            if (existingRoom != null && existingRoom.getHotel().getId() == hotel.getId()) {
                session.remove(existingRoom);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Room> getRoomsForHotel(Hotel hotel) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Room where hotel.id = :hotelId", Room.class)
                    .setParameter("hotelId", hotel.getId())
                    .list();
        }
    }
}

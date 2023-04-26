package facades;

import javax.persistence.EntityManager;

import dtos.FactDTO;
import dtos.QuoteDTO;
import dtos.UserDTO;

import entities.Fact;
import entities.Quote;
import entities.User;

import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;


    /**
     *
     * Rename Class to a relevant name Add add relevant facade methods
     */
    public class InternalApiFacade {

        private static InternalApiFacade instance;
        private static EntityManagerFactory emf;

        //Private Constructor to ensure Singleton
        private InternalApiFacade() {}


        /**
         *
         * @param _emf
         * @return an instance of this facade class.
         */
        public static InternalApiFacade getInternalApiFacade(EntityManagerFactory _emf) {
            if (instance == null) {
                emf = _emf;
                instance = new InternalApiFacade();
            }
            return instance;
        }

        private EntityManager getEntityManager() {
            return emf.createEntityManager();
        }

        //Creates Quote
        public QuoteDTO createQuote(QuoteDTO qdto){
            Quote q = new Quote(qdto.getQuote());
            EntityManager em = getEntityManager();
            try {
                em.getTransaction().begin();
                em.persist(q);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
            return new QuoteDTO(q);
        }

        //Creates Fact
        public FactDTO createFact(FactDTO fdto){
            Fact f = new Fact(fdto.getFact());
            EntityManager em = getEntityManager();
            try {
                em.getTransaction().begin();
                em.persist(f);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
            return new FactDTO(f);
        }

        //Removes Quote
        public UserDTO removeQuote(String username, int QuoteId){
            EntityManager em = getEntityManager();
            User u = em.find(User.class, username);
            Quote q = em.find(Quote.class, QuoteId);
            if(u == null || q == null)
                throw new IllegalArgumentException("user or Quote not found");
            u.removeQuote(q);
            try {
                em.getTransaction().begin();
                em.merge(u);
                //em.merge(h);
                em.getTransaction().commit();
            }catch (Exception e){
                System.out.println("No user or quote with that id found");
                return null;
            } finally {
                em.close();
            }
            return new UserDTO(u);
        }

        //Removes Fact
        public FactDTO removeFact(String username, int FactId){
            EntityManager em = getEntityManager();
            User u = em.find(User.class, username);
            Fact f = em.find(Fact.class, FactId);
            if(u == null || f == null)
                throw new IllegalArgumentException("user or Fact not found");
            u.removeFact(f);
            try {
                em.getTransaction().begin();
                em.merge(u);
                //em.merge(h);
                em.getTransaction().commit();
            }catch (Exception e){
                System.out.println("No user or Fact with that id found");
                return null;
            } finally {
                em.close();
            }
            return new FactDTO(f);
        }

        //Adds Quote
        public UserDTO addQuote(String username, int QuoteId){
            EntityManager em = getEntityManager();
            User u = em.find(User.class, username);
            Quote q = em.find(Quote.class, QuoteId);
            if(u == null || q == null)
                throw new IllegalArgumentException("user or Quote not found");
            u.addQuote(q);
            try {
                em.getTransaction().begin();
                em.merge(u);
                //em.merge(h);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
            return new UserDTO(u);
        }

        //Adds Fact
        public FactDTO addFact(String username, int FactId){
            EntityManager em = getEntityManager();
            User u = em.find(User.class, username);
            Fact f = em.find(Fact.class, FactId);
            if(u == null || f == null)
                throw new IllegalArgumentException("user or Fact not found");
            u.addFact(f);
            try {
                em.getTransaction().begin();
                em.merge(u);
                //em.merge(h);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
            return new FactDTO(f);
        }

        //Get All Quotes
        public List<QuoteDTO> getAll(){
            EntityManager em = emf.createEntityManager();
            TypedQuery<Quote> query = em.createQuery("SELECT q FROM Quote q", Quote.class);
            List<Quote> quotes = query.getResultList();
            return QuoteDTO.getDtos(quotes);
        }

        //Get All Facts
        public List<FactDTO> getAllFacts(){
            EntityManager em = emf.createEntityManager();
            TypedQuery<Fact> query = em.createQuery("SELECT f FROM Fact f", Fact.class);
            List<Fact> facts = query.getResultList();
            return FactDTO.getDtos(facts);
        }


        public static void main(String[] args) {
            emf = EMF_Creator.createEntityManagerFactory();
            InternalApiFacade iaf = getInternalApiFacade(emf);
            iaf.getAll().forEach(dto->System.out.println(dto));

//        QuoteDTO qdto = new QuoteDTO(new Quote("test"));
//        iaf.createQuote(qdto);
//        iaf.getAll().forEach(dto->System.out.println(dto));

//        iaf.addQuote("user", 2L);
            iaf.addQuote("user", 2);
            iaf.addFact("user", 2);
        }

    }


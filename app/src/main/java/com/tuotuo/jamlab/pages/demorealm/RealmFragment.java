package com.tuotuo.jamlab.pages.demorealm;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.tuotuo.jamlab.R;
import com.tuotuo.jamlab.pages.base.ContentFragment;
import com.tuotuo.jamlab.pages.demorealm.bean.Cat;
import com.tuotuo.jamlab.pages.demorealm.bean.Dog;
import com.tuotuo.jamlab.pages.demorealm.bean.Person;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by liuzhenhui on 2016/10/31.
 */
public class RealmFragment extends ContentFragment {
    public static final String TAG = RealmFragment.class.getSimpleName();

    @BindView(R.id.ll_realm_container)
    LinearLayout llRealmRoot;

    private Realm realm;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_realm;
    }

    @Override
    protected void onInitView() {
        llRealmRoot.removeAllViews();
        realm = Realm.getDefaultInstance();
        // These operations are small enough that
        // we can generally safely run them on the UI thread.
        // 基础增删改查
        basicCRUD(realm);
        // 条件查询
        basicQuery(realm);
        // 关联查询
        basicLinkQuery(realm);

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String info;
                info = complexReadWrite();
                info += complexQuery();
                subscriber.onNext(info);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        showStatus(s);
                    }
                });
    }

    private void basicCRUD(Realm realm) {
        showStatus("Perform basic Create/Read/Update/Delete (CRUD) operations...");

        // All writes must be wrapped in a transaction to facilitate safe multi threading
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Add a person
                Person person = realm.createObject(Person.class);
                person.setId(1);
                person.setName("Young Person");
                person.setAge(14);
            }
        });

        // Find the first person (no query conditions) and read a field
        final Person person = realm.where(Person.class).findFirst();
        showStatus(person.getName() + ":" + person.getAge());

        // Update person in a transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                person.setName("Senior Person");
                person.setAge(99);
                showStatus(person.getName() + " got older: " + person.getAge());
            }
        });

        // Delete all persons
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(Person.class);
            }
        });
    }

    private void basicQuery(Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Person person = realm.createObject(Person.class);
                person.setId(3);
                person.setName("BasicQuery");
                person.setAge(98);
            }
        });
        showStatus("\nPerforming basic Query operation...");
        showStatus("Number of persons: " + realm.where(Person.class).count());

        RealmResults<Person> results = realm.where(Person.class).equalTo("age", 99).findAll();

        showStatus("Size of result set: " + results.size());
    }

    private void basicLinkQuery(Realm realm) {
        showStatus("\nPerforming basic Link Query operation...");
        showStatus("Number of persons: " + realm.where(Person.class).count());

        RealmResults<Person> results = realm.where(Person.class).equalTo("cats.name", "Tiger").findAll();

        showStatus("Size of result set: " + results.size());
    }

    private String complexReadWrite() {
        String status = "\nPerforming complex Read/Write operation...";

        // Open the default realm. All threads must use it's own reference to the realm.
        // Those can not be transferred across threads.
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dog fido = realm.createObject(Dog.class);
                fido.name = "fido";
                for (int i = 0; i < 10; i++) {
                    Person person = realm.createObject(Person.class);
                    person.setId(i);
                    person.setName("Person no." + i);
                    person.setAge(i);
                    person.setDog(fido);

                    // The field tempReference is annotated with @Ignore.
                    // This means setTempReference sets the Person tempReference
                    // field directly. The tempReference is NOT saved as part of
                    // the RealmObject:
                    person.setTempReference(42);

                    for (int j = 0; j < i; j++) {
                        Cat cat = realm.createObject(Cat.class);
                        cat.name = "Cat_" + j;
                        person.getCats().add(cat);
                    }
                }
            }
        });

        // Implicit read transactions allow you to access your objects
        status += "\nNumber of persons: " + realm.where(Person.class).count();

        // Iterate over all objects
        for (Person pers : realm.where(Person.class).findAll()) {
            String dogName;
            if (pers.getDog() == null) {
                dogName = "None";
            } else {
                dogName = pers.getDog().name;
            }
            status += "\n" + pers.getName() + ":" + pers.getAge() + " : " + dogName + " : " + pers.getCats().size();
        }

        //Sorting
        RealmResults<Person> sortedPersons = realm.where(Person.class).findAllSorted("age", Sort.DESCENDING);
        status += "\nSorting " + sortedPersons.last().getName() + " == " + realm.where(Person.class).findFirst()
                .getName();

        realm.close();
        return status;
    }

    private String complexQuery() {
        String status = "\n\nPerforming complex Query operation...";

        Realm realm = Realm.getDefaultInstance();

        status += "\nNumber of persons: " + realm.where(Person.class).count();

        //Find all persons where age between 7 and 9 and name begins with "Person"
        RealmResults<Person> results = realm.where(Person.class)
                .between("age", 7, 9)
                .beginsWith("name", "Person")
                .findAll();
        status += "\nSize of result set: " + results.size();

        realm.close();
        return status;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void showStatus(String txt) {
        TextView textView = new TextView(getMainActivity());
        textView.setText(txt);
        llRealmRoot.addView(textView);
    }
}

package com.example.german.bookstoreapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.german.bookstoreapp.R;
import com.example.german.bookstoreapp.domain.Book;
import com.example.german.bookstoreapp.interfaces.BooksListener;
import com.example.german.bookstoreapp.service.BookService;

/**
 * Created by german on 16/11/17.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookHolder> {

    private Cursor mCursor;
    private Context mContext;
    private BooksListener mBookLister;

    public BooksAdapter(Cursor cursor, Context context) {
        super();
        mCursor = cursor;
        mContext = context;
    }

    public class BookHolder extends RecyclerView.ViewHolder {
        public TextView bookNameTextView;
        public ImageButton deleteBookButton;
        public TextView bookAuthorTextView;
        public ImageButton editBookButton;

        public BookHolder(View itemView) {
            super(itemView);
            bookNameTextView = (TextView) itemView.findViewById(R.id.book_name_tv);
            bookAuthorTextView = (TextView) itemView.findViewById(R.id.book_author_tv);
            deleteBookButton = (ImageButton) itemView.findViewById(R.id.delete_book_button);
            editBookButton = (ImageButton) itemView.findViewById(R.id.edit_book_button);
        }
    }

    @Override
    public BooksAdapter.BookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, parent, false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(BooksAdapter.BookHolder holder, int position) {
        if (mCursor != null) {
            mCursor.moveToPosition(position);
            final Book book = new Book(mCursor);
            holder.bookNameTextView.setText(book.getBookName());
            holder.bookAuthorTextView.setText(book.getBookAuthor());
            holder.deleteBookButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    long bookId = book.getBookId();
                    Intent intent = new Intent(mContext, BookService.class);
                    intent.putExtra(BookService.EXTRA_ID, bookId);
                    intent.setAction(BookService.ACTION_DELETE);
                    mContext.startService(intent);
                    if (mBookLister != null) {
                        mBookLister.onUpdate();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if(mCursor != null) {
            count = mCursor.getCount();
        }
        return count;
    }

    public void setCursor(Cursor cursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = cursor;
        notifyDataSetChanged();
    }

    public void setBooksListener(BooksListener booksListener) {
        mBookLister = booksListener;
    }
}

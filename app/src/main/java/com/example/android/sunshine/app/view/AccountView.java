package com.example.android.sunshine.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import com.example.android.sunshine.app.model.AccountDataRecord;

/**
 * Created by jeff on 8/8/15.
 */
public class AccountView extends View {
    private static final String TAG = "AccountView";

    protected Paint bgPaint = new Paint();
    protected Paint fgPaint = new Paint();
    protected Paint textPaint = new Paint();
    protected Paint centerLinePaint = new Paint();

    protected AccountDataRecord dataRecord = null;

    public AccountView(Context context, android.util.AttributeSet attributes) {
        super(context, attributes);

        this.bgPaint.setColor(Color.BLACK);
        this.bgPaint.setAntiAlias(true);
        this.fgPaint.setColor(Color.DKGRAY);
        this.fgPaint.setAntiAlias(true);
        this.textPaint.setColor(Color.WHITE);
        this.textPaint.setAntiAlias(true);
        this.textPaint.setTextSize(30);
        this.textPaint.setTextAlign(Paint.Align.RIGHT);
        this.textPaint.setAntiAlias(true);
        this.centerLinePaint.setColor(Color.WHITE);
        this.centerLinePaint.setAntiAlias(true);
        this.centerLinePaint.setStrokeWidth(2.0f);

        int width = 200;
        int height = 55;

        setLayoutParams(new ViewGroup.LayoutParams(width, height));
    }

    public AccountView(Context context) {
        super(context);

        this.bgPaint.setColor(Color.BLACK);
        this.bgPaint.setAntiAlias(true);
        this.fgPaint.setColor(Color.DKGRAY);
        this.fgPaint.setAntiAlias(true);
        this.textPaint.setColor(Color.WHITE);
        this.textPaint.setAntiAlias(true);
        this.textPaint.setTextSize(30);
        this.textPaint.setTextAlign(Paint.Align.RIGHT);
        this.textPaint.setAntiAlias(true);
        this.centerLinePaint.setColor(Color.WHITE);
        this.centerLinePaint.setAntiAlias(true);
        this.centerLinePaint.setStrokeWidth(2.0f);

        int width = 200;
        int height = 55;

        setLayoutParams(new ViewGroup.LayoutParams(width, height));
    }

    public AccountDataRecord getDataRecord() {
        return dataRecord;
    }

    public void setDataRecord(AccountDataRecord dataRecord) {
        this.dataRecord = dataRecord;
    }

    @Override
    public void onDraw(Canvas canvas) {
        int margin = 8;
        int yLine1 = 22;
        int yLine2 = 50;

        canvas.drawRect(0, 0, 4000, 4000, bgPaint);

        int width = canvas.getWidth();
        int center = width / 2;

        if (dataRecord != null) {
            String name = dataRecord.getName();
            int value = dataRecord.getValue();
            String detail = dataRecord.getDetail();

            if (value > 0) {
                this.textPaint.setTextAlign(Paint.Align.LEFT);

                canvas.drawRect(center, 10, center + value, 50, fgPaint);

                canvas.drawText(name, center + value + margin, yLine1, textPaint);
                canvas.drawText(detail, center + value + margin, yLine2, textPaint);
            } else if (value < 0) {
                this.textPaint.setTextAlign(Paint.Align.RIGHT);

                canvas.drawRect(center + value, 10, center, 50, fgPaint);

                canvas.drawText(name, center + value - margin, yLine1, textPaint);
                canvas.drawText(detail, center + value - margin, yLine2, textPaint);
            }

            canvas.drawLine(center, 0, center, 300, centerLinePaint);
        } else {
            canvas.drawText("No data", width - 10, 20, textPaint);
        }
    }
}

package com.example.plantteacher;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import kotlin.jvm.internal.Intrinsics;

public final class Classfier {

    //classify plant diseases
    private Interpreter INTERPRETER;
    private List LABEL_LIST;
    private int INPUT_SIZE;
    private int PIXEL_SIZE;
    private int IMAGE_MEAN;
    private float IMAGE_STD;
    private int MAX_RESULTS;
    private float THRESHOLD;


    private MappedByteBuffer loadModelFile(AssetManager assetManager, String modelPath) throws IOException {
        AssetFileDescriptor fileDescriptor = assetManager.openFd(modelPath);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();

        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    //list of platns diseases
    private List loadLabelList() {

        List classes = new ArrayList<>();

        classes.add("apple apple scab");
        classes.add("apple black rot");
        classes.add("apple cedar apple rust");
        classes.add("apple healthy");
        classes.add("blueberry healthy");
        classes.add("cherry including sour powdery mildew");
        classes.add("cherry including sour healthy");
        classes.add("corn maize cercospora leaf spot gray leaf spot");
        classes.add("corn maize common rust");
        classes.add("corn maize northern leaf blight");
        classes.add("corn maize healthy");
        classes.add("grape black rot");
        classes.add("grape esca black measles");
        classes.add("grape leaf blight isariopsis leaf spot");
        classes.add("grape healthy");
        classes.add("orange haunglongbing citrus greening");
        classes.add("peach bacterial spot");
        classes.add("peach healthy");
        classes.add("pepper bell bacterial spot");
        classes.add("pepper bell healthy");
        classes.add("potato early blight");
        classes.add("potato late blight");
        classes.add("potato healthy");
        classes.add("raspberry healthy");
        classes.add("soybean healthy");
        classes.add("squash powdery mildew");
        classes.add("strawberry leaf scorch");
        classes.add("strawberry healthy");
        classes.add("tomato bacterial spot");
        classes.add("tomato early blight");
        classes.add("tomato late blight");
        classes.add("tomato leaf mold");
        classes.add("tomato septoria leaf spot");
        classes.add("tomato spider mites two spotted spider mite");
        classes.add("tomato target spot");
        classes.add("tomato tomato yellow leaf curl virus");
        classes.add("tomato tomato mosaic virus");
        classes.add("tomato healthy");

        return classes;
    }

    public List recognizeImage(Bitmap bitmap)
    {
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE,INPUT_SIZE, false);
        ByteBuffer byteBuffer = convertBitmapToByteBuffer(scaledBitmap);

        byte var5 = 1;
        float[][] var6 = new float[var5][];

        for(int i = 0;i<1;i++)
        {
            float[] value = new float[LABEL_LIST.size()];
        }
        for(int var7 = 0; var7 < var5; ++var7) {
            float[] var12 = new float[this.LABEL_LIST.size()];
            var6[var7] = var12;
        }

        float[][] result = (float[][])var6;
        this.INTERPRETER.run(byteBuffer, result);
        return getSortedResult(result);
    }

    private final ByteBuffer convertBitmapToByteBuffer(Bitmap bitmap) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * INPUT_SIZE * INPUT_SIZE * PIXEL_SIZE);
        byteBuffer.order(ByteOrder.nativeOrder());
        int[] intValues = new int[INPUT_SIZE * INPUT_SIZE];
        bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        int pixel = 0;

        for(int i = 0;i<INPUT_SIZE;i++)
        {
            for(int j = 0;j<INPUT_SIZE;j++)
            {
                int value = intValues[pixel++];
                byteBuffer.putFloat((float)((value >> 16 & 255) - this.IMAGE_MEAN) / this.IMAGE_STD);
                byteBuffer.putFloat((float)((value >> 8 & 255) - this.IMAGE_MEAN) / this.IMAGE_STD);
                byteBuffer.putFloat((float)((value & 255) - this.IMAGE_MEAN) / this.IMAGE_STD);
            }
        }
        return byteBuffer;
    }


    private final List getSortedResult(float[][] labelProbArray) {

        //get the best result
        PriorityQueue pq = new PriorityQueue(MAX_RESULTS);

        int recognitionsSize;
        for(int i = 0;i<LABEL_LIST.size();i++)
        {
            float confidence = labelProbArray[0][i];
            if(confidence >= THRESHOLD)
            {
                pq.add(new Recognition("" + i, this.LABEL_LIST.size() > i ? (String)LABEL_LIST.get(i) : "Unknown", confidence));
            }
        }

        ArrayList recognitions = new ArrayList();
        recognitionsSize = Math.min(pq.size(), MAX_RESULTS);
        int var16 = 0;

        for(int i = 0;i<recognitionsSize;i++)
        {
            recognitions.add(pq.poll());
        }

        return (List)recognitions;
    }

    public Classfier(AssetManager assetManager, String modelPath, int inputSize) throws IOException {

        this.INPUT_SIZE = inputSize;
        this.PIXEL_SIZE = 3;
        this.IMAGE_STD = 255.0F;
        this.MAX_RESULTS = 3;
        this.THRESHOLD = 0.4F;
        this.INTERPRETER = new Interpreter(this.loadModelFile(assetManager, modelPath));
        this.LABEL_LIST = this.loadLabelList();
    }

    public static final class Recognition {
        @NotNull
        private String id;
        @NotNull
        private String title;
        private float confidence;

        @NotNull
        public String toString() {
            return "Title = " + this.title + ", Confidence = " + this.confidence + ')';
        }

        @NotNull
        public final String getId() {
            return this.id;
        }

        public final void setId(@NotNull String var1) {
            Intrinsics.checkNotNullParameter(var1, "<set-?>");
            this.id = var1;
        }

        @NotNull
        public final String getTitle() {
            return this.title;
        }

        public final void setTitle(@NotNull String var1) {
            Intrinsics.checkNotNullParameter(var1, "<set-?>");
            this.title = var1;
        }

        public final float getConfidence() {
            return this.confidence;
        }

        public final void setConfidence(float var1) {
            this.confidence = var1;
        }

        public Recognition(@NotNull String id, @NotNull String title, float confidence) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(title, "title");
            this.id = id;
            this.title = title;
            this.confidence = confidence;
        }

        // $FF: synthetic method
        public Recognition(String var1, String var2, float var3, int var4) {
            this(var1, var2, var3);
            if ((var4 & 1) != 0) {
                var1 = "";
            }

            if ((var4 & 2) != 0) {
                var2 = "";
            }

            if ((var4 & 4) != 0) {
                var3 = 0.0F;
            }


        }

        public Recognition() {
            this((String)null, (String)null, 0.0F, 7);
        }

        @NotNull
        public final String component1() {
            return this.id;
        }

        @NotNull
        public final String component2() {
            return this.title;
        }

        public final float component3() {
            return this.confidence;
        }

        @NotNull
        public final Recognition copy(@NotNull String id, @NotNull String title, float confidence) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(title, "title");
            return new Recognition(id, title, confidence);
        }

        // $FF: synthetic method
        public static Recognition copy$default(Recognition var0, String var1, String var2, float var3, int var4, Object var5) {
            if ((var4 & 1) != 0) {
                var1 = var0.id;
            }

            if ((var4 & 2) != 0) {
                var2 = var0.title;
            }

            if ((var4 & 4) != 0) {
                var3 = var0.confidence;
            }

            return var0.copy(var1, var2, var3);
        }

        public int hashCode() {
            String var10000 = this.id;
            int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
            String var10001 = this.title;
            return (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31 + Float.floatToIntBits(this.confidence);
        }

        public boolean equals(@Nullable Object var1) {
            if (this != var1) {
                if (var1 instanceof Recognition) {
                    Recognition var2 = (Recognition)var1;
                    if (Intrinsics.areEqual(this.id, var2.id) && Intrinsics.areEqual(this.title, var2.title) && Float.compare(this.confidence, var2.confidence) == 0) {
                        return true;
                    }
                }

                return false;
            } else {
                return true;
            }
        }
    }

}
